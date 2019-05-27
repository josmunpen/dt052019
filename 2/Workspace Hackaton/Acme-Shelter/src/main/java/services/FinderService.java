
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Adopter;
import domain.Customisation;
import domain.Finder;
import domain.Pet;

@Service
@Transactional
public class FinderService {

	@Autowired
	AdopterService			adopterService;

	@Autowired
	FinderRepository		finderRepository;

	@Autowired
	PetService				petService;

	@Autowired
	CustomisationService	customisationService;


	private boolean checkAdopter() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADOPTER);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Finder findOne(final Integer id) {
		return this.finderRepository.findOne(id);
	}

	public Finder getFinder() {
		Assert.isTrue(this.checkAdopter());
		final Adopter a = this.adopterService.findOnePrincipal();
		return a.getFinder();
	}

	public boolean checkCache(final Finder finder) {
		Assert.isTrue(this.checkAdopter());
		boolean res = false;
		final Date today = Calendar.getInstance().getTime();
		final Date moment = finder.getMoment();
		final List<Customisation> cusList = new ArrayList<>(this.customisationService.findAll());
		final Customisation cus = cusList.get(0);
		final long milisecondsDiff = Math.abs(today.getTime() - moment.getTime());
		final long hoursDiff = milisecondsDiff / 3600000;

		if (hoursDiff > cus.getFinderDuration())
			res = true;
		return res;
	}

	public Finder save(final Finder finder) {
		final Finder res = finder;
		Assert.isTrue(this.checkAdopter());
		if (finder.getId() != 0) {
			final Adopter principal = this.adopterService.findOnePrincipal();
			Assert.isTrue(principal.getFinder().getId() == finder.getId());
			final Customisation cust = this.customisationService.getCustomisation();
			final int resultsNumber = cust.getResultsNumber();

			final List<Pet> rez = this.petService.finderResults(finder);
			List<Pet> results = new ArrayList<>(rez);
			if (results.size() > resultsNumber)
				results = results.subList(0, resultsNumber);

			res.setPets(results);
			final Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, -1);
			res.setMoment(now.getTime());
		}
		return this.finderRepository.save(res);
	}

	public Finder clear(final Finder finder) {
		Assert.isTrue(this.checkAdopter());
		Assert.notNull(finder);
		final Finder res = finder;
		res.setSex("");
		res.setType("");
		res.setKeyword("");
		res.setAge(null);
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		res.setMoment(now.getTime());
		res.setPets(new ArrayList<Pet>());
		return this.finderRepository.save(res);
	}


	@Autowired
	private Validator	validator;


	public Finder reconstruct(final Finder finder, final BindingResult binding) {
		Finder res;
		Assert.isTrue(this.checkAdopter());
		if (finder.getId() == 0)
			res = finder;
		else {
			res = this.findOne(finder.getId());
			res.setType(finder.getType());
			res.setKeyword(finder.getKeyword());
			res.setAge(finder.getAge());
			res.setSex(finder.getSex());
		}
		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

}
