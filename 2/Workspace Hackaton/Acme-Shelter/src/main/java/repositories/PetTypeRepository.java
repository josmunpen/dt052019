
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Integer> {

	@Query("select pt from PetType pt where pt.name = ?1 or pt.nombre = ?1")
	public PetType findOneName(String name);
}
