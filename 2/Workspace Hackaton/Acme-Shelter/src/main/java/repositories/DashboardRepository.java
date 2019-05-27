
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	@Query("select avg(1.0*(select count(p) from Pet p where p.petOwner.id =o.id group by p.petOwner)) from PetOwner o")
	public Double avgPets();

	@Query("select min(1*(select count(p) from Pet p where p.petOwner.id =o.id group by p.petOwner)) from PetOwner o")
	public Integer minPets();

	@Query("select max(1*(select count(p) from Pet p where p.petOwner.id =o.id group by p.petOwner)) from PetOwner o")
	public Integer maxPets();

	@Query("select stddev(1.0*(select count(p) from Pet p where p.petOwner.id =o.id group by p.petOwner)) from PetOwner o")
	public Double stddevPets();

	@Query("select avg(1.0*(select count(a) from Application a where a.pet.id =p.id group by a.pet)) from Pet p")
	public Double avgApplications();

	@Query("select min(1*(select count(a) from Application a where a.pet.id =p.id group by a.pet)) from Pet p")
	public Integer minApplications();

	@Query("select max(1*(select count(a) from Application a where a.pet.id =p.id group by a.pet)) from Pet p")
	public Integer maxApplications();

	@Query("select stddev(1.0*(select count(a) from Application a where a.pet.id =p.id group by a.pet)) from Pet p")
	public Double stddevApplications();

	@Query("select avg(p.age) from Pet p where p.age is not null")
	public Double avgAge();

	@Query("select min(p.age) from Pet p where p.age is not null")
	public Integer minAge();

	@Query("select max(p.age) from Pet p where p.age is not null")
	public Integer maxAge();

	@Query("select stddev(p.age) from Pet p where p.age is not null")
	public Double stddevAge();

	@Query("select p.petType.name from Pet p group by p.petType order by count(p)")
	public List<String> top3TypesEng();

	@Query("select p.petType.nombre from Pet p group by p.petType order by count(p)")
	public List<String> top3TypesEsp();

	@Query("select 1.0 * count(a)/(select count(a1) from Application a1) from Application a where a.status='PENDING'")
	public Double ratioPendingApplications();

	@Query("select 1.0 * count(a)/(select count(a1) from Application a1) from Application a where a.status='REJECTED'")
	public Double ratioRejectedApplications();

	@Query("select 1.0 * count(a)/(select count(a1) from Application a1) from Application a where a.status='ACCEPTED'")
	public Double ratioAcceptedApplications();

	@Query("select avg(p.histories.size) from Pet p")
	public Double avgHistories();

	@Query("select min(p.histories.size) from Pet p")
	public Integer minHistories();

	@Query("select max(p.histories.size) from Pet p")
	public Integer maxHistories();

	@Query("select stddev(p.histories.size) from Pet p")
	public Double stddevHistories();

	@Query("select avg(1.0*(select count(m) from MedicalCheckUp m where m.pet.id =p.id group by m.pet)) from Pet p")
	public Double avgMedicalCheckUp();

	@Query("select min(1*(select count(m) from MedicalCheckUp m where m.pet.id =p.id group by m.pet)) from Pet p")
	public Integer minMedicalCheckUp();

	@Query("select max(1*(select count(m) from MedicalCheckUp m where m.pet.id =p.id group by m.pet)) from Pet p")
	public Integer maxMedicalCheckUp();

	@Query("select stddev(1.0*(select count(m) from MedicalCheckUp m where m.pet.id =p.id group by m.pet)) from Pet p")
	public Double stddevMedicalCheckUp();

	@Query("select 1.0 * count(m)/(select count(p) from Pet p) from MedicalCheckUp m")
	public Double ratioPetsWithMedicalCheckUp();

	@Query("select m.veterinarian.userAccount.username from MedicalCheckUp m group by m.veterinarian order by count(m)")
	public List<String> top3Veterinarians();

	@Query("select a.adopter.userAccount.username from Application a where a.status='ACCEPTED' group by a.adopter order by count(a)")
	public List<String> top3Adopters();

}
