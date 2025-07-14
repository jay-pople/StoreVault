package project.storeapp.service.actorservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.storeapp.dto.actor.*;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.User;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.OwnerRepository;
import project.storeapp.repository.UserRepository;

@Service
public class RegistrationService {

	
	
	@Autowired
	PasswordEncoder encoder; 
    
	@Autowired
    private ActorRepository actorRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    public ResponseEntity<?> signup(ActorDTO actorDto) {
        ActorCredentials credentials = actorDto.getCredentials();
        
        ActorDetails details=actorDto.getDetails();
        String username = credentials.getUsername();

        Optional<Actor> existing = actorRepo.findByUsername(username);

        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        String role = credentials.getRole().toLowerCase();

        Actor actor = new Actor();
        actor.setUsername(credentials.getUsername());
        actor.setPassword(encoder.encode(credentials.getPassword()));
        actor.setRole(role);
        actorRepo.save(actor);

        if (role.equals("user")) {
            User user = new User();
            user.setActor(actor); // assume @OneToOne mapping
            user.setName(details.getName());
            user.setGmail(details.getGmail());
            user.setPhoneNo(details.getPhoneno());
            userRepo.save(user);
        } else if (role.equals("owner")) {
            Owner owner = new Owner();
            owner.setActor(actor);
            owner.setName(details.getName());
            owner.setGmail(details.getGmail());
            owner.setPhoneNo(details.getPhoneno());
            ownerRepo.save(owner);
        } else {
            return ResponseEntity.badRequest().body("Invalid role");
        }

        return ResponseEntity.ok("Signup successful");
    }
}
