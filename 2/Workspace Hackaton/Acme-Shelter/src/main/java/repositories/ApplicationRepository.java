package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Adopter;
import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.pet.id=?1")
	List<Application> findByPet(int petId);

	@Query("select a from Application a where a.adopter = ?1")
	Collection<Application> findAppsByAdopter(Adopter a);
}
