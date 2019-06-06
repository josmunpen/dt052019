
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ApplicationRepository;
import security.LoginService;
import domain.Adopter;
import domain.Application;
import domain.Pet;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	ApplicationRepository	ar;

	@Autowired
	private HistoryService	hs;

	@Autowired
	private AdopterService	adopterService;

	@Autowired
	private PetService		petService;

	@Autowired
	Validator				validator;


	public void delete(final Pet p) {
		for (final Application a : this.ar.findByPet(p.getId()))
			this.ar.delete(a.getId());

	}

	public Application findOne(final int applicationid) {
		return this.ar.findOne(applicationid);
	}

	public Collection<Application> findAll() {
		return this.ar.findAll();
	}

	public Application save(final Application ap) {
		ap.setStatus("PENDING");
		final Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -1);
		ap.setMoment(now.getTime());

		Assert.isTrue(this.checkAdopterPets(ap.getPet().getId()));

		final Application res = this.ar.save(ap);
		return res;
	}

	public Collection<Application> myApplicationList() {
		final Collection<Application> all = new ArrayList<Application>();
		final Collection<Application> applications = new ArrayList<Application>();
		all.addAll(this.findAll());
		for (final Application a : all)
			if (a.getPet().getPetOwner().getId() == this.hs.getThisPetOwner().getId())
				if (!a.getStatus().equals("SUBMITTED"))
					applications.add(a);

		return applications;
	}

	public Application create() {
		final Application a = new Application();
		a.setStatus("PENDING");
		//		a.setRejectCause("");
		final Date moment = new Date(System.currentTimeMillis() - 1);
		a.setMoment(moment);
		return a;
	}

	public Application saveStatus(final Application application) {
		final Application a = this.checkRejected(application);

		return this.ar.save(a);
	}

	public Collection<Application> findAppsByAdopter(final Adopter a) {
		return this.ar.findAppsByAdopter(a);
	}

	public Application checkRejected(final Application a) {
		if (a.getRejectCause().equals(","))
			a.setRejectCause("");

		if (a.getStatus().equals("REJECTED") && a.getRejectCause().isEmpty())
			a.setStatus("PENDING");
		if (a.getStatus().equals("ACCEPTED"))
			a.setRejectCause("");
		return a;
	}

	public Boolean checkAdopterPets(final int petId) {
		Boolean res = true;
		final Pet p = this.petService.findOne(petId);
		final Adopter a = this.adopterService.findByUserAccount(LoginService.getPrincipal());
		final Collection<Application> appsByAdopter = this.findAppsByAdopter(a);

		for (final Application app : appsByAdopter)
			if (app.getPet().equals(p) && !app.getStatus().equals("REJECTED")) {
				res = false;
				return res;
			}
		return res;
	}

	public Application checkRejectedTest(final Application a) {
		if (a.getRejectCause().equals(","))
			a.setRejectCause("");

		if (a.getStatus().equals("REJECTED") && a.getRejectCause().isEmpty())
			throw new IllegalArgumentException();
		if (a.getStatus().equals("ACCEPTED"))
			a.setRejectCause("");
		return a;
	}

	public Application reconstructStatus(final Application a) {
		final Application res = this.findOne(a.getId());
		res.setStatus(a.getStatus());
		res.setRejectCause(a.getRejectCause());

		return res;
	}

	public Application reconstruct(final Application ap, final BindingResult binding) {
		Application res;

		if (ap.getId() == 0) {
			final Date moment = new Date(System.currentTimeMillis() - 1);
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

	public String deleteWeirdCommas(final String s) {
		final int n = s.length();
		int i = 0;
		final char comma = ',';
		String result = s;
		if (n != 0)
			while (i < n)
				if (!s.isEmpty() && s != null) {
					final char testComma = s.charAt(i);
					if (testComma == comma)
						result = s.substring(i + 1);
					else
						break;
					i++;
				}
		return result;
	}

}
