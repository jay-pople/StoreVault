package project.storeapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.storeapp.model.entity.Actor;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

	public Optional<Actor> findByUsername(String username);
}
