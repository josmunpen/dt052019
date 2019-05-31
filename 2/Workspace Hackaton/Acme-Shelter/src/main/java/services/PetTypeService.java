
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PetTypeRepository;
import domain.PetType;

@Service
@Transactional
public class PetTypeService {

	@Autowired
	private PetTypeRepository	typeRepository;


	public PetType create() {
		final PetType res = new PetType();
		res.setChilds(new ArrayList<PetType>());
		res.setFinalMode(false);
		return res;
	}

	public PetType save(final PetType pt) {
		//Provisional
		Assert.isTrue(pt.getName() != "");
		Assert.isTrue(pt.getNombre() != "");
		return this.typeRepository.save(pt);
	}
	public PetType findOneName(final String name) {
		Assert.isTrue(name != "" && name != null);
		return this.typeRepository.findOneName(name);
	}

	public PetType findOne(final int id) {
		return this.typeRepository.findOne(id);
	}

	public void delete(final PetType petType) {
		if (petType.getChilds() != null)
			for (final PetType p : petType.getChilds())
				this.delete(p);
		this.typeRepository.delete(petType.getId());

	}
	public List<PetType> findAll() {
		return this.typeRepository.findAll();
	}

	public List<PetType> findPetsFinalMode() {
		return (List<PetType>) this.typeRepository.findPetsFinalMode();
	}
}
