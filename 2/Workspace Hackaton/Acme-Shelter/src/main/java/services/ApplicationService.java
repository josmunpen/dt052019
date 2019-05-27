
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
	
	public Application save(Application application){
		return ar.save(application);
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
	
	
	public Application saveStatus(Application application){
		Application a = checkRejected(application);
		
		return ar.save(a);
	}
	

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
	
	

}
