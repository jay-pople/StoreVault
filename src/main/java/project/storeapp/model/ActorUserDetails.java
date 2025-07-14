package project.storeapp.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import project.storeapp.model.entity.Actor;

public class ActorUserDetails implements UserDetails {

	
	Actor actor;
	
	public ActorUserDetails(Actor actor) {
		this.actor=actor;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(actor.getRole()));
	}

	@Override
	public String getPassword() {
		return actor.getPassword();
	}

	@Override
	public String getUsername() {
		return actor.getUsername();
	}

}
