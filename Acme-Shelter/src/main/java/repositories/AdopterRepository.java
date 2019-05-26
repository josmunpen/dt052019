package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Adopter;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Integer>{
	
	@Query("select a from Adopter a where a.userAccount.id = ?1")
	Adopter findByUserAccountId(int userAccount);

}
