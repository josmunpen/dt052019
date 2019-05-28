
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Veterinarian;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer> {

	@Query("select v from Veterinarian v where v.userAccount.id = ?1")
	Veterinarian findByUserAccountId(int userAccount);
}
