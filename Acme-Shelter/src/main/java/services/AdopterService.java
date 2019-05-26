package services;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Adopter;

import repositories.AdopterRepository;
import security.UserAccount;

@Service
@Transactional
public class AdopterService {
	
	@Autowired
	private AdopterRepository adopterRepository;
	
	public Collection<Adopter> findAll() {
		return adopterRepository.findAll();
	}
	
	public Adopter findOne(int id) {
		Assert.isTrue(id != 0);
		return adopterRepository.findOne(id);
	}
	
	public Adopter findByUserAccount(UserAccount ua) {
		Assert.notNull(ua);
		Adopter a = adopterRepository.findByUserAccountId(ua.getId());
		return a;
	}

}
