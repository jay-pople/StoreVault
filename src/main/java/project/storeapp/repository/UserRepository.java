package project.storeapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByActor(Actor actor);

}
