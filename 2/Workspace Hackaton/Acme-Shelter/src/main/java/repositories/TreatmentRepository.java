
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;
import domain.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

	@Query("select m.treatments from MedicalCheckUp m where m.id=?1")
	List<Treatment> findByMedicalCheckUp(int mId);

	@Query("select m.treatments from MedicalCheckUp m where m.veterinarian.id=?1")
	List<Treatment> findAllByVeterinarian(int id);

	@Query("select m.pet from MedicalCheckUp m join m.treatments t where t.id=?1")
	Pet findPetByTreatmentId(int id);

	@Query("select m.treatments from MedicalCheckUp m where m.pet.id=?1")
	List<Treatment> findByPet(int id);
}
