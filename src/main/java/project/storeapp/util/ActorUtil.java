package project.storeapp.util;

import org.springframework.security.core.context.SecurityContextHolder;

import project.storeapp.model.entity.Actor;
import project.storeapp.repository.ActorRepository;

public class ActorUtil {

    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Actor getActor(ActorRepository repository) {
        String username = getUsername();
        return repository.findByUsername(username).orElseThrow(() -> new RuntimeException("Actor not found"));
    }
}

