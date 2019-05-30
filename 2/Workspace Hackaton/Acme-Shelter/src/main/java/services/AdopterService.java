
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdopterRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Adopter;
import domain.Customisation;
import domain.SocialProfile;
import forms.AdopterForm;

@Service
@Transactional
public class AdopterService {

	@Autowired
	public AdopterRepository	adopterRepository;

	@Autowired
	public ActorService2		actorService;

	@Autowired
	public CustomisationService	customisationService;

	@Autowired
	public SocialProfileService	socialprofileService;


	//Constructor
	public AdopterService() {
		super();
	}

	public Adopter create() {

		Adopter result;
		result = new Adopter();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.ADOPTER);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Adopter

		return result;
	}

	public Adopter findByPrincipal() {
		Adopter res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Adopter findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.adopterRepository.findOne(a.getId());
	}

	public Adopter findOne(final Adopter Adopter) {
		return this.adopterRepository.findOne(Adopter.getId());
	}

	public Collection<Adopter> findAll() {
		return this.adopterRepository.findAll();
	}

	public Adopter save(final Adopter Adopter) {
		Assert.notNull(Adopter);

		final String pnumber = Adopter.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			Adopter.setPhoneNumber(cc.concat(pnumber));

		if (Adopter.getId() != 0) {
			Assert.isTrue(this.actorService.checkAdopter());

			// Modified Adopter must be logged Adopter
			final Adopter logAdopter;
			logAdopter = this.findByPrincipal();
			Assert.notNull(logAdopter);
			Assert.notNull(logAdopter.getId());

		} else {

			//TODO: DESCOMENTAR
			//			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			//			Adopter.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = Adopter.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = Adopter.getUserAccount();
			cuenta.setPassword(hash);
			Adopter.setUserAccount(cuenta);
		}
		// Restrictions
		Adopter res;

		res = this.adopterRepository.save(Adopter);
		return res;
	}

	public Adopter findByUserAccount(final UserAccount userAccount) {
		Adopter res;
		Assert.notNull(userAccount);

		res = this.adopterRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Adopter findOne(final int AdopterId) {
		Adopter c;

		Assert.notNull(AdopterId);
		Assert.isTrue(AdopterId != 0);
		c = this.adopterRepository.findOne(AdopterId);

		Assert.notNull(c);
		return c;
	}

	public Adopter reconstruct(final AdopterForm adopterForm, final BindingResult binding) {
		final Adopter adopter = this.create();

		//Assert.isTrue(AdopterForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.ADOPTER);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = adopterForm.getUserAccount();
		ua.setAuthorities(aus);

		adopter.setAddress(adopterForm.getAddress());
		adopter.setEmail(adopterForm.getEmail());
		adopter.setName(adopterForm.getName());
		adopter.setPhoneNumber(adopterForm.getPhoneNumber());
		adopter.setPhoto(adopterForm.getPhoto());
		adopter.setSurname(adopterForm.getSurname());
		adopter.setDescription(adopterForm.getDescription());

		adopter.setUserAccount(ua);
		adopter.setHolderName(adopterForm.getHolderName());
		adopter.setMakeName(adopterForm.getMakeName());
		adopter.setNumber(adopterForm.getNumber());
		adopter.setExpirationMonth(adopterForm.getExpirationMonth());
		adopter.setExpirationYear(adopterForm.getExpirationYear());
		adopter.setCvv(adopterForm.getCvv());

		this.validator.validate(adopter, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return adopter;
	}


	@Autowired
	private Validator	validator;


	public Adopter reconstruct(final Adopter adopter, final BindingResult binding) {
		Adopter res;

		//Check authority
		final Authority a = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.ADOPTER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (adopter.getId() == 0)
			res = adopter;
		else
			res = this.adopterRepository.findOne(adopter.getId());
		res.setName(adopter.getName());
		res.setEmail(adopter.getEmail());
		res.setSurname(adopter.getSurname());
		res.setAddress(adopter.getAddress());
		res.setPhoneNumber(adopter.getPhoneNumber());
		res.setPhoto(adopter.getPhoto());

		res.setHolderName(adopter.getHolderName());
		res.setMakeName(adopter.getMakeName());
		res.setNumber(adopter.getNumber());
		res.setExpirationMonth(adopter.getExpirationMonth());
		res.setExpirationYear(adopter.getExpirationYear());
		res.setCvv(adopter.getCvv());
		res.setDescription(adopter.getDescription());
		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void leave() {
		final Adopter logAdopter = this.findByPrincipal();

		logAdopter.setAddress("Unknown");
		logAdopter.setEmail("unknown@unknown.com");
		logAdopter.setName("Unknown");
		logAdopter.setPhoneNumber("Unknown");
		logAdopter.setPhoto("http://www.unknown.com");
		logAdopter.setSocialProfiles(null);
		logAdopter.setSurname("Unknown");
		logAdopter.setDescription("Unknown");

		logAdopter.setHolderName("Unknown");
		logAdopter.setMakeName("Unknown");
		logAdopter.setCvv(123);
		logAdopter.setExpirationMonth(1);
		logAdopter.setExpirationYear(9999);
		logAdopter.setNumber("4532134223318979");

		final UserAccount ua = logAdopter.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();

		if (logAdopter.getSocialProfiles() != null)
			for (final SocialProfile sp : logAdopter.getSocialProfiles())
				this.socialprofileService.deleteLeave(sp);
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logAdopter.setUserAccount(ua);
	}

	public void flush() {
		this.adopterRepository.flush();
	}

}
