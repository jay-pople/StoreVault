package project.storeapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

	Optional<Owner> findByActor(Actor actor);

}
