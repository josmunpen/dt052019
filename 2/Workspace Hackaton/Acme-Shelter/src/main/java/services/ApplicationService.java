
package services;

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
	ApplicationRepository	applicationRepository;


	public void delete(final Pet p) {
		for (final Application a : this.applicationRepository.findByPet(p.getId()))
			this.applicationRepository.delete(a.getId());

	}

}
