
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PetTypeRepository;
import domain.PetType;

@Service
@Transactional
public class PetTypeService {

	@Autowired
	PetTypeRepository	petTypeRepository;


	public void delete(final PetType petType) {
		if (petType.getChilds() != null)
			for (final PetType p : petType.getChilds())
				this.delete(p);
		this.petTypeRepository.delete(petType.getId());

	}

	public List<PetType> findAll() {
		return this.petTypeRepository.findAll();

	}
}
