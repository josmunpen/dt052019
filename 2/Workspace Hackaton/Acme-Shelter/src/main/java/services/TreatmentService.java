
package services;

import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TreatmentRepository;
import domain.MedicalCheckUp;
import domain.Pet;
import domain.Treatment;

@Service
@Transactional
public class TreatmentService {

	@Autowired
	TreatmentRepository		treatmentRepository;

	@Autowired
	ActorService2			actorService;

	@Autowired
	MedicalCheckUpService	medicalCheckUpService;

	@Autowired
	PetService				petService;

	@Autowired
	Validator				validator;


	public void delete(final MedicalCheckUp m) {
		for (final Treatment t : this.treatmentRepository.findByMedicalCheckUp(m.getId()))
			this.treatmentRepository.delete(t.getId());
	}

	public List<Treatment> findAllByVeterinarian(final int id) {
		Assert.isTrue(this.actorService.checkVeterinarian());
		return this.treatmentRepository.findAllByVeterinarian(id);
	}

	public Treatment findOne(final int treatmentId) {
		Assert.isTrue(this.actorService.checkVeterinarian() || this.actorService.checkPetOwner());

		final Treatment t = this.treatmentRepository.findOne(treatmentId);
		if (this.actorService.checkVeterinarian())
			Assert.isTrue(t.getMedicalCheckUp().getVeterinarian().getId() == this.actorService.findByPrincipal().getId());
		if (this.actorService.checkPetOwner())
			Assert.isTrue(this.petService.findAllByPetOwner(this.actorService.findByPrincipal().getId()).contains(this.findPetByTreatmentId(t.getId())));
		return t;
	}
	private Pet findPetByTreatmentId(final int id) {
		Assert.isTrue(this.actorService.checkVeterinarian());
		return this.treatmentRepository.findPetByTreatmentId(id);
	}

	public Treatment create(final int medicalId) {
		this.actorService.checkVeterinarian();
		final Treatment res = new Treatment();
		res.setMedicalCheckUp(this.medicalCheckUpService.findOne(medicalId));
		res.setId(0);

		return res;
	}

	public Treatment reconstruct(final Treatment t, final BindingResult binding) {
		Treatment res;
		if (t.getId() == 0) {
			res = t;
			res.setMoment(Calendar.getInstance().getTime());
		} else {
			res = this.findOne(t.getId());
			res.setIllness(t.getIllness());
			res.setTreatmentC(t.getTreatmentC());
			res.setComment(t.getComment());
		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();
		return res;
	}

	public void save(final Treatment t) {
		Assert.isTrue(this.actorService.checkVeterinarian());
		Assert.isTrue(this.findAllByVeterinarian(this.actorService.findByPrincipal().getId()).contains(t));

		this.treatmentRepository.save(t);

	}

	public void delete(final Treatment t1) {
		Assert.isTrue(this.actorService.checkVeterinarian() || this.actorService.checkPetOwner());
		if (this.actorService.checkVeterinarian())
			Assert.isTrue(this.findAllByVeterinarian(this.actorService.findByPrincipal().getId()).contains(t1));
		if (this.actorService.checkPetOwner())
			Assert.isTrue(this.petService.findAllByPetOwner(this.actorService.findByPrincipal().getId()).contains(t1.getMedicalCheckUp().getPet()));
		this.treatmentRepository.delete(t1.getId());

	}
	public List<Treatment> findByPet(final Pet pet) {

		return this.treatmentRepository.findByPet(pet.getId());
	}

	public List<Treatment> findByMedicalCheckUp(final MedicalCheckUp m) {
		// TODO Auto-generated method stub
		return this.treatmentRepository.findByMedicalCheckUp(m.getId());
	}

}
