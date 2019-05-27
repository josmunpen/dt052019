
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Application a where a.pet.id=?1")
	List<Application> findByPet(int petId);

}
