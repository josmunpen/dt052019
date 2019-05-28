
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

	@Query("select m.treatments from MedicalCheckUp m where m.id=?1")
	List<Treatment> findByMedicalCheckUp(int mId);
}