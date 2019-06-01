
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;
import domain.PetType;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	@Query("select p from Pet p where p.petOwner.id = ?1")
	List<Pet> findByPetOwnerId(int petOwnerId);
	//Search
	@Query("select p from Pet p where p.name like %?1% or p.petType.name like %?1% or p.petType.nombre like %?1% or p.status like %?1% or p.nature like %?1% or p.careRequirements like %?1% or p.dietRequirements like %?1% or p.petsRequirements like %?1% or p.familyRequirements like %?1% or p.sex like %?1%")
	public List<Pet> searchPets(String word);

	//Finder
	@Query("select p from Pet p where p.name like %?1% or p.address like %?1% or p.identifier like %?1%")
	public Collection<Pet> finderKeyword(String keyword);

	@Query("select p from Pet p where p.petType = ?1")
	public Collection<Pet> finderType(PetType petType);

	@Query("select p from Pet p where p.age <= ?1")
	public Collection<Pet> finderAge(Integer age);

	@Query("select p from Pet p where p.sex = ?1")
	public Collection<Pet> finderSex(String sex);

	@Query("select p from Pet p where p not in (select m.pet from MedicalCheckUp m))")
	public Collection<Pet> findWithoutMedicalCheckUp();

	@Query("select p from Pet p where p not in (select a.pet from Application a where a.status = 'ACCEPTED')")
	public Collection<Pet> findWithoutApplicationAccepted();

}
