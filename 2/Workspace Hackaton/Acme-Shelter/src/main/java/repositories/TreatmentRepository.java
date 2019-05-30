
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;
import domain.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

	@Query("select t from Treatment t where t.medicalCheckUp.id=?1")
	List<Treatment> findByMedicalCheckUp(int mId);

	@Query("select t from Treatment t where t.medicalCheckUp.veterinarian.id=?1")
	List<Treatment> findAllByVeterinarian(int id);

	@Query("select t.medicalCheckUp.pet from Treatment t where t.id=?1")
	Pet findPetByTreatmentId(int id);

	@Query("select t from Treatment t where t.medicalCheckUp.pet.id=?1")
	List<Treatment> findByPet(int id);
}
