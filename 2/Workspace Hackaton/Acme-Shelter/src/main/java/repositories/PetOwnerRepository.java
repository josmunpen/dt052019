
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PetOwner;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {

	@Query("select p from PetOwner p where p.userAccount.id = ?1")
	PetOwner findByUserAccountId(int userAccount);

}
