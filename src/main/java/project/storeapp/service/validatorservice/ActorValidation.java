package project.storeapp.service.validatorservice;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class ActorValidation {

	
	public boolean isOwner(Authentication authentication) {
	    return authentication.getAuthorities().stream()
	            .anyMatch(auth -> auth.getAuthority().equals("owner"));
	}
	
	public boolean isUser(Authentication authentication) {
	    return authentication.getAuthorities().stream()
	            .anyMatch(auth -> auth.getAuthority().equals("user"));
	}

}
