
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MedicalCheckUpRepository;
import domain.MedicalCheckUp;
import domain.Pet;

@Service
@Transactional
public class MedicalCheckUpService {

	@Autowired
	MedicalCheckUpRepository	medicalCheckUpRepository;


	public void delete(final MedicalCheckUp m) {
		this.medicalCheckUpRepository.delete(m.getId());
	}

	public List<MedicalCheckUp> findByPet(final Pet p) {
		return this.medicalCheckUpRepository.findByPet(p.getId());
	}
}
