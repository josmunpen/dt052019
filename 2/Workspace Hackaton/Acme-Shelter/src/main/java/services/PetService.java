
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PetRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Finder;
import domain.History;
import domain.MedicalCheckUp;
import domain.Pet;
import domain.PetOwner;
import domain.PetType;

@Service
@Transactional
public class PetService {

	@Autowired
	PetRepository			petRepository;

	@Autowired
	PetTypeService			petTypeService;

	@Autowired
	TreatmentService		treatmentService;

	@Autowired
	MedicalCheckUpService	medicalCheckUpService;

	@Autowired
	HistoryService			historyService;

	@Autowired
	ActorService			actorService;

	@Autowired
	PetOwnerService			petOwnerService;

	@Autowired
	ApplicationService		applicationService;

	@Autowired
	Validator				validator;


	private boolean checkAdopter() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADOPTER);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Collection<Pet> findAll() {
		return this.petRepository.findAll();
	}

	//Search
	public List<Pet> searchPet(final String keyword) {
		return this.petRepository.searchPets(keyword);
	}

	//Finder
	public Collection<Pet> finderKeyword(final String keyword) {
		Assert.isTrue(this.checkAdopter());
		return this.petRepository.finderKeyword(keyword);
	}

	public List<Pet> findAllByPetOwner(final Integer petOwnerId) {
		Assert.isTrue(this.petOwnerService.checkPetOwner());
		return this.petRepository.findByPetOwnerId(petOwnerId);
	}
	public Collection<Pet> finderSex(final String sex) {
		Assert.isTrue(this.checkAdopter());
		return this.petRepository.finderSex(sex);
	}

	public Pet findOne(final int petId) {

		final Pet p = this.petRepository.findOne(petId);

		return p;
	}

	public Collection<Pet> finderAge(final Integer age) {
		Assert.isTrue(this.checkAdopter());
		return this.petRepository.finderAge(age);
	}
	private boolean checkPetOwner() {
		final Actor a = this.actorService.findByPrincipal();
		final PetOwner p = this.petOwnerService.findOne(a.getId());

		final Authority a2 = new Authority();
		a2.setAuthority(Authority.PETOWNER);

		return p.getUserAccount().getAuthorities().contains(a2);
	}

	public Collection<Pet> finderType(final String ptname) {
		Assert.isTrue(this.checkAdopter());
		final PetType pt = this.petTypeService.findOneName(ptname);
		return this.petRepository.finderType(pt);
	}

	public Pet create() {
		this.checkPetOwner();
		final Pet res = new Pet();
		res.setId(0);
		res.setIdentifier(TickerGenerator.generateTicker());
		return res;
	}
	public Pet reconstruct(final Pet p, final BindingResult binding) {
		Pet res;
		if (p.getId() == 0) {
			res = p;
			final Actor a = this.actorService.findByPrincipal();
			final PetOwner po = this.petOwnerService.findOne(a.getId());
			res.setPetOwner(po);
		} else {
			res = this.findOne(p.getId());
			res.setAddress(p.getAddress());
			res.setAge(p.getAge());
			res.setCareRequirements(p.getCareRequirements());
			res.setDietRequirements(p.getDietRequirements());
			res.setFamilyRequirements(p.getFamilyRequirements());
			res.setManagementCost(p.getManagementCost());
			res.setName(p.getName());
			res.setNature(p.getNature());
			res.setPedigree(p.getPedigree());
			res.setPetsRequirements(p.getPetsRequirements());
			res.setPetType(p.getPetType());
			res.setPhotos(p.getPhotos());
			res.setSex(p.getSex());
			res.setStatus(p.getStatus());
		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();
		return res;
	}

	public List<Pet> finderResults(final Finder finder) {
		Assert.isTrue(this.checkAdopter());
		final List<Pet> res = new ArrayList<>(this.findAll());
		if (finder.getKeyword() != null && finder.getKeyword() != "")
			res.retainAll(this.finderKeyword(finder.getKeyword()));
		if (finder.getSex() != null && finder.getSex() != "")
			res.retainAll(this.finderSex(finder.getSex()));
		if (finder.getType() != null && finder.getType() != "")
			res.retainAll(this.finderType(finder.getType()));
		if (finder.getAge() != null)
			res.retainAll(this.finderAge(finder.getAge()));
		return res;
	}
	public void flush() {
		this.petRepository.flush();
	}

	public void save(final Pet p) {
		Assert.isTrue(this.petOwnerService.checkPetOwner());

		Assert.isTrue(p.getPetOwner().getId() == this.actorService.findByPrincipal().getId());

		this.petRepository.save(p);
	}
	public void delete(final Pet p1) {
		Assert.isTrue(this.petOwnerService.checkPetOwner());
		Assert.isTrue(p1.getPetOwner().getId() == this.actorService.findByPrincipal().getId());
		for (final History h : p1.getHistories())
			this.historyService.delete(h);
		for (final MedicalCheckUp m : this.medicalCheckUpService.findByPet(p1)) {
			this.treatmentService.delete(m);
			this.medicalCheckUpService.delete(m);
		}
		this.applicationService.delete(p1);
		this.petRepository.delete(p1);
	}

}
