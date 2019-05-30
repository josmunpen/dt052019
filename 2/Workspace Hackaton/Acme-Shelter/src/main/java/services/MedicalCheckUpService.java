
package services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MedicalCheckUpRepository;
import domain.MedicalCheckUp;
import domain.Pet;
import domain.Treatment;
import domain.Veterinarian;

@Service
@Transactional
public class MedicalCheckUpService {

	@Autowired
	MedicalCheckUpRepository	medicalCheckUpRepository;

	@Autowired
	ActorService2				actorService;

	@Autowired
	VeterinarianService			veterinarianService;


	public void delete(final MedicalCheckUp m) {
		this.medicalCheckUpRepository.delete(m.getId());
	}

	public List<MedicalCheckUp> findByPet(final Pet p) {
		return this.medicalCheckUpRepository.findByPet(p.getId());
	}

	public List<MedicalCheckUp> findByVeterinarian(final int id) {
		return this.medicalCheckUpRepository.findByVeterinarian(id);
	}

	public MedicalCheckUp create() {
		Assert.isTrue(this.actorService.checkVeterinarian());
		final MedicalCheckUp res = new MedicalCheckUp();
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		res.setMoment(now.getTime());
		return res;
	}

	public MedicalCheckUp save(final MedicalCheckUp checkUp) {
		Assert.notNull(checkUp);
		Assert.isTrue(this.actorService.checkVeterinarian());
		Veterinarian logVeterinarian;
		logVeterinarian = this.veterinarianService.findByPrincipal();

		if (checkUp.getId() != 0) {

			// Veterinarian must be check up owner

			Assert.notNull(logVeterinarian);
			Assert.notNull(logVeterinarian.getId());

		}
		// Restrictions
		MedicalCheckUp res = new MedicalCheckUp();

		if (checkUp.getId() == 0) {
			final Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, -1);
			checkUp.setMoment(now.getTime());
			checkUp.setVeterinarian(logVeterinarian);
		}
		res = this.medicalCheckUpRepository.save(checkUp);
		return res;
	}
	public MedicalCheckUp findByTreatment(final Treatment t1) {
		return this.medicalCheckUpRepository.findByTreatment(t1.getId());

	}

}
