package project.storeapp.service.actorservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.storeapp.model.ActorUserDetails;
import project.storeapp.model.entity.Actor;
import project.storeapp.repository.ActorRepository;

@Service
public class ActorUserDetailsService implements UserDetailsService {

	
	@Autowired
	ActorRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	Optional<Actor> actor=repo.findByUsername(username);
	
	if (actor.isPresent())
	{
		return new ActorUserDetails(actor.get());
	}
	throw new UsernameNotFoundException("username not found");
	}

}
