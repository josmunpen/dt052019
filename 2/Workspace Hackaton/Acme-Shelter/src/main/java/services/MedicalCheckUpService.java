
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MedicalCheckUpRepository;
import domain.MedicalCheckUp;
import domain.Pet;
import domain.Veterinarian;
import domain.Treatment;

@Service
@Transactional
public class MedicalCheckUpService {

	@Autowired
	MedicalCheckUpRepository	medicalCheckUpRepository;

	@Autowired
	ActorService2				actorService;


	public void delete(final MedicalCheckUp m) {
		this.medicalCheckUpRepository.delete(m.getId());
	}

	public List<MedicalCheckUp> findByPet(final Pet p) {
		return this.medicalCheckUpRepository.findByPet(p.getId());
	}

	public List<MedicalCheckUp> findByVeterinarian(final int id) {
		return this.medicalCheckUpRepository.findByVeterinarian(id);

	public MedicalCheckUp create() {
		Assert.isTrue(this.actorService.checkVeterinarian());
		final MedicalCheckUp res = new MedicalCheckUp();
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		res.setMoment(now.getTime());
		return res;
	}
	

	
		public MedicalCheckUp findByTreatment(final Treatment t1) {
		return this.medicalCheckUpRepository.findByTreatment(t1.getId());

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

	public MedicalCheckUp findByTreatment(final Treatment t1) {
		return this.medicalCheckUpRepository.findByTreatment(t1.getId());

	}

	public void save(final MedicalCheckUp m1) {
		Assert.isTrue(this.actorService.checkVeterinarian());
		Assert.isTrue(this.findByVeterinarian(this.actorService.findByPrincipal().getId()).contains(m1));

		this.medicalCheckUpRepository.save(m1);
	}
}
