package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
import domain.Pet;

@Service
@Transactional
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
	public Collection<Pet> findAll() {
		return petRepository.findAll();
	}
	
	public Pet findOne(int id) {
		Assert.isTrue(id != 0);
		return petRepository.findOne(id);
	}

}
