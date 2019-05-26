package services;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		a.setRejectCause("");
		return a;
	}
	
	@SuppressWarnings("deprecation")
	public Application save(Application ap) {
		ap.setStatus("PENDING");
		Date moment = new Date();
		moment.setSeconds(moment.getSeconds()-1);
		ap.setMoment(moment);
		
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
	
	
}
