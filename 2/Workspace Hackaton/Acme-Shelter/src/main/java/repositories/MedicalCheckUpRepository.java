
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.MedicalCheckUp;

@Repository
public interface MedicalCheckUpRepository extends JpaRepository<Actor, Integer> {

	@Query("select m from MedicalCheckUp m where m.pet.id=?1")
	List<MedicalCheckUp> findByPet(int id);

}
