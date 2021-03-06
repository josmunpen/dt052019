
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import repositories.HistoryRepository;
import repositories.PetOwnerRepository;
import security.LoginService;
import security.UserAccount;
import domain.History;
import domain.Pet;
import domain.PetOwner;

@Service
@Transactional
public class HistoryService {

	@Autowired
	HistoryRepository	historyRepository;
	
	@Autowired
	private PetOwnerRepository petOwnerRepository;
	
	@Autowired
	private PetService petService;
	


	public void delete(final History h) {
		this.historyRepository.delete(h.getId());

	}
	
	public History create(){
		return new History();
	}
	
	public Collection<History> findAll(){
		return historyRepository.findAll();
	}
	
	public History findOne(int historyId){
		return historyRepository.findOne(historyId);
	}
	
	public Pet save(History history, Pet pet){
		checkDate(history);
		history.setActor(getThisPetOwner());
		History res = null;
		Pet p = this.petService.findOne(pet.getId());
		if(p.getHistories()==null){
			Collection<History> his = new ArrayList<History>();
			p.setHistories(his);
		}
		res = historyRepository.save(history);
		Collection<History> histories = p.getHistories();
		if(histories.contains(history)){
			histories.remove(history);
			histories.add(res);
		}else{
			histories.add(res);
		}
		p.setHistories(histories);
		
		return p;
		
	}
	
	private void checkDate(History history) {
		Date actual = new Date();
		if(history.getEndMoment()==null){
			if(history.getStartMoment().after(actual)){
				throw new IllegalArgumentException();
			}
		}else{
		if(history.getStartMoment().after(actual) ||history.getEndMoment().after(actual)){
			throw new IllegalArgumentException();
		}
		if(history.getStartMoment().after(history.getEndMoment())){
			throw new IllegalArgumentException();
		}
		}
		
	}

	public void deleteH(History history){
		historyRepository.delete(history);
	}
	
	
	public PetOwner getThisPetOwner() {
		PetOwner res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.petOwnerRepository.findByUserAccountId(userAccount.getId());
		return res;
	}

	public Collection<History> getHistoriesByPet(Pet pet) {
		Collection<History> histories = new ArrayList<History>();
		histories = pet.getHistories();
		return histories;
	}


	public void deleteHistoryOfPet(final History history, final Pet pet) {
		final Pet p = this.petService.findOne(pet.getId());
		final List<History> histories = new ArrayList<History>();
		histories.addAll(p.getHistories());
		histories.remove(history);
		p.setHistories(histories);
		this.deleteH(history);

	}
	
	public History reconstructHistory(History history){
		History res;
		if(history.getId()==0) {
			return history;
		}
		res = this.findOne(history.getId());
		res.setDescription(history.getDescription());
		res.setStartMoment(history.getStartMoment());
		res.setEndMoment(history.getEndMoment());
		
		return res;
	}

	public boolean checkAnterior(History history, Pet pet) {
	    Boolean res = true;
		List<History> list = new ArrayList<History>();
		History ant = null;
		list.addAll(this.getHistoriesByPet(pet));
		if(list.isEmpty()) return true;
		if(list.size()==1) return true;
		if(history.getId()==0){
			ant = list.get(list.size()-1);
			if(ant.getEndMoment().after(history.getStartMoment())) return false;
		}else{
			ant = list.get(list.indexOf(history)-1);
			if(ant.getEndMoment().after(history.getStartMoment())) return false;
		}
		return res;
	}
	
	public History getLastHistory(Pet pet){
		if(pet.getHistories().isEmpty()) return null;
		List<History> list = new ArrayList<History>();
		list.addAll(this.getHistoriesByPet(pet));
		return list.get(list.size()-1);
	}
}
