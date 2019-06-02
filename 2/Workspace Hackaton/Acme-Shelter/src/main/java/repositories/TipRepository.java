
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Tip;

@Repository
public interface TipRepository extends JpaRepository<Tip, Integer> {

}
