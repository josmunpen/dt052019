package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Veterinarian;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer>{

}
