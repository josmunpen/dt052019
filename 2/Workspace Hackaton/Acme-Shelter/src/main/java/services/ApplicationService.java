package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ApplicationRepository;
import domain.Application;
import domain.Pet;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	ApplicationRepository	ar;
	
	@Autowired
	private HistoryService hs;

	@Autowired
	private AdopterService adopterService;


	public void delete(final Pet p) {
		for (final Application a : this.ar.findByPet(p.getId()))
			this.ar.delete(a.getId());

	}
	
	public Application findOne(int applicationid) {
		return this.ar.findOne(applicationid);
	}
	
	public Collection<Application> findAll(){
		return ar.findAll();
	}
	
	public Application save(Application ap) {
		ap.setStatus("PENDING");
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		ap.setMoment(now.getTime());
		
		Assert.isTrue(checkAdopterPets(ap.getPet().getId()));
		
		Application res = ar.save(ap);
		return res;
	}
	
	public Collection<Application> myApplicationList(){
		Collection<Application> all = new ArrayList<Application>();
		Collection<Application> applications = new ArrayList<Application>();
		all.addAll(this.findAll());
		for(Application a : all){
			if(a.getPet().getPetOwner().getId()==hs.getThisPetOwner().getId()){
				if(!a.getStatus().equals("SUBMITTED")){
					applications.add(a);
				}
			}
		}
		
		return applications;
	}
	
	public Application create() {
		Application a = new Application();
		a.setStatus("PENDING");
//		a.setRejectCause("");
		Date moment = new Date(System.currentTimeMillis()-1);
		a.setMoment(moment);
		return a;
	}
	
	public Application saveStatus(Application application){
		Application a = checkRejected(application);
		
		return ar.save(a);
	}
	
	public Collection<Application> findAppsByAdopter(Adopter a) {
		return applicationRepository.findAppsByAdopter(a);

	public Application checkRejected(Application a) {
		if(a.getRejectCause().equals(",")) a.setRejectCause("");
		
		if(a.getStatus().equals("REJECTED") && a.getRejectCause().isEmpty()){
			a.setStatus("PENDING");
		}
		if(a.getStatus().equals("ACCEPTED")){
			a.setRejectCause("");
		}
		return a;
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
	
	public Application checkRejectedTest(Application a) {
		if(a.getRejectCause().equals(",")) a.setRejectCause("");
		
		if(a.getStatus().equals("REJECTED") && a.getRejectCause().isEmpty()){
			throw new IllegalArgumentException();
		}
		if(a.getStatus().equals("ACCEPTED")){
			a.setRejectCause("");
		}
		return a;
	}
	
	public Application reconstructStatus(Application a){
		Application res = this.findOne(a.getId());
		res.setStatus(a.getStatus());
		res.setRejectCause(a.getRejectCause());
		
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
