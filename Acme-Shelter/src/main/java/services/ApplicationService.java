package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ApplicationRepository;
import security.LoginService;
import domain.Adopter;
import domain.Application;
import domain.Pet;

@Service
@Transactional
public class ApplicationService {

	//Repositories
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private AdopterService adopterService;
	
	@Autowired
	private Validator validator;
	
	//Methods
	public Application findOne(int id) {
		Assert.isTrue(id != 0);
		return applicationRepository.findOne(id);
	}
	
	public Collection<Application> findAll() {
		return applicationRepository.findAll();
	}
	
	public Application create() {
		Application a = new Application();
		a.setStatus("PENDING");
//		a.setRejectCause("");
		Date moment = new Date(System.currentTimeMillis()-1);
		a.setMoment(moment);
		return a;
	}
	
	public Application save(Application ap) {
		ap.setStatus("PENDING");
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		ap.setMoment(now.getTime());
		
		Assert.isTrue(checkAdopterPets(ap.getPet().getId()));
		
		Application res = applicationRepository.save(ap);
		return res;
	}
	
	public Collection<Application> findAppsByAdopter(Adopter a) {
		return applicationRepository.findAppsByAdopter(a);
	}
	
	public Boolean checkAdopterPets(int petId) {
		Boolean res = true;
		Pet p = petService.findOne(petId);
		Adopter a = adopterService.findByUserAccount(LoginService.getPrincipal());
		Collection<Application> appsByAdopter = this.findAppsByAdopter(a);
		
		for (Application app : appsByAdopter) {
			if (app.getPet().equals(p) && !app.getStatus().equals("REJECTED")) {
				res = false;
				return res;
			}
		}
		return res;
	}
	
	public Application reconstruct(Application ap, BindingResult binding) {
		Application res;
		
		if (ap.getId()==0) {
			Date moment = new Date(System.currentTimeMillis()-1);
			ap.setMoment(moment);
			ap.setStatus("PENDING");
			res = ap;
		} else {
			res = this.findOne(ap.getId());
			res.setAdopter(ap.getAdopter());
			res.setMoment(ap.getMoment());
			res.setPet(ap.getPet());
			res.setStatus(ap.getStatus());
			res.setRejectCause(ap.getRejectCause());
		}
		this.validator.validate(res, binding);
		return res;
	}
	
}
