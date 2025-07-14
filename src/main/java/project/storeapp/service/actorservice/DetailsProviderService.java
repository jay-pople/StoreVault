package project.storeapp.service.actorservice;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.storeapp.dto.actor.*;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.User;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.OwnerRepository;
import project.storeapp.repository.UserRepository;

@Service
public class DetailsProviderService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    @Autowired
    private ActorRepository actorRepo;
    
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(15);

    public ResponseEntity<?> getDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

        Optional<Actor> actorOpt = actorRepo.findByUsername(username);
        if (actorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
        }

        Actor actor = actorOpt.get();
        long id = actor.getId();

        for (GrantedAuthority role : roles) {
            switch (role.getAuthority()) {
                case "user":
                    return userRepo.findById(id)
                            .map(user -> ResponseEntity.ok(buildDto(user.getName(), user.getGmail(), user.getPhoneNo(),user.getId())))
                            .orElse(new ResponseEntity<ActorDTO>(HttpStatus.BAD_REQUEST));

                case "owner":
                    return ownerRepo.findById(id)
                            .map(owner -> ResponseEntity.ok(buildDto(owner.getName(), owner.getGmail(), owner.getPhoneNo(),owner.getId())))
                            .orElse(new ResponseEntity<ActorDTO>(HttpStatus.BAD_REQUEST));
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
    }

    private ActorDTO buildDto(String name, String gmail, String phoneNo,Long id) {
        ActorDetails details = new ActorDetails();
        details.setName(name);
        details.setGmail(gmail);
        details.setPhoneno(phoneNo);
        details.setId(id);

        ActorDTO dto = new ActorDTO();
        dto.setDetails(details);
        return dto;
    }

    
    
    @Transactional
    public ResponseEntity<?> updateDetails(ActorDTO actorDto) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Actor> result = actorRepo.findByUsername(name);

        if (result.isPresent()) {
            Actor actor = result.get(); // use existing entity

            // Update credentials if present
            ActorCredentials credentials = actorDto.getCredentials();
            if (credentials != null) {
                actor.setUsername(credentials.getUsername());
                actor.setPassword(passwordEncoder.encode(credentials.getPassword())); // hash password
            }

            // Update details based on role
            ActorDetails details = actorDto.getDetails();
            if (details != null) {
                String role = result.get().getRole();

                if ("USER".equalsIgnoreCase(role)) {
                    Optional<User> userOpt = userRepo.findByActor(actor);
                    if (userOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found for actor");
                    }

                    User user = userOpt.get();
                    user.setName(details.getName());
                    user.setPhoneNo(details.getPhoneno());
                    user.setGmail(details.getGmail());
                    userRepo.save(user);

                } else if ("OWNER".equalsIgnoreCase(role)) {
                    Optional<Owner> ownerOpt = ownerRepo.findByActor(actor);
                    if (ownerOpt.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Owner not found for actor");
                    }

                    Owner owner = ownerOpt.get();
                    owner.setName(details.getName());
                    owner.setPhoneNo(details.getPhoneno());
                    owner.setGmail(details.getGmail());
                    ownerRepo.save(owner);

                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role");
                }
            }

            actorRepo.save(actor); // update actor credentials
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Actor updated successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Actor cannot be updated");
    }


}
