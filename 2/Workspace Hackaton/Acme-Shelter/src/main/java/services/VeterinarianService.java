
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

import repositories.VeterinarianRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.Box;
import domain.SocialProfile;
import domain.Veterinarian;
import forms.VeterinarianForm;

@Service
@Transactional
public class VeterinarianService {

	@Autowired
	public VeterinarianRepository	veterinarianRepository;

	@Autowired
	public ActorService2			actorService;

	//TODO:DESCOMENTAR
	//	@Autowired
	//	public CustomisationService		customisationService;
	//
	//	@Autowired
	//	public SocialProfileService		socialprofileService;

	@Autowired
	private MessageService			messageService;


	//Constructor
	public VeterinarianService() {
		super();
	}

	public Veterinarian create() {

		Veterinarian result;
		result = new Veterinarian();
		final Collection<Box> predefined = new ArrayList<Box>();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.VETERINARIAN);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Veterinarian

		return result;
	}

	public Veterinarian findByPrincipal() {
		Veterinarian res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Veterinarian findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.veterinarianRepository.findOne(a.getId());
	}

	public Veterinarian findOne(final Veterinarian Veterinarian) {
		return this.veterinarianRepository.findOne(Veterinarian.getId());
	}

	public Collection<Veterinarian> findAll() {
		return this.veterinarianRepository.findAll();
	}

	public Veterinarian save(final Veterinarian Veterinarian) {
		Assert.notNull(Veterinarian);

		final String pnumber = Veterinarian.getPhoneNumber();
		//TODO: Descomentar
		//		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		//		final String cc = cus.getPhoneNumberCode();
		//		if (pnumber.matches("^[0-9]{4,}$"))
		//			Veterinarian.setPhoneNumber(cc.concat(pnumber));

		if (Veterinarian.getId() != 0) {
			Assert.isTrue(this.actorService.checkAdmin() || this.actorService.checkVeterinarian());

			// Modified Veterinarian must be logged Veterinarian
			final Veterinarian logVeterinarian;
			logVeterinarian = this.findByPrincipal();
			Assert.notNull(logVeterinarian);
			Assert.notNull(logVeterinarian.getId());

		} else {

			//TODO: DESCOMENTAR
			//			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			//	Veterinarian.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = Veterinarian.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = Veterinarian.getUserAccount();
			cuenta.setPassword(hash);
			Veterinarian.setUserAccount(cuenta);
		}
		// Restrictions
		Veterinarian res;

		res = this.veterinarianRepository.save(Veterinarian);
		return res;
	}

	public Veterinarian findByUserAccount(final UserAccount userAccount) {
		Veterinarian res;
		Assert.notNull(userAccount);

		res = this.veterinarianRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Veterinarian saveForTest(final Veterinarian bro) {

		// Restrictions

		if (bro.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = bro.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = bro.getUserAccount();
			cuenta.setPassword(hash);
			bro.setUserAccount(cuenta);

		}
		if (bro.getId() == 0) {

		}

		return this.veterinarianRepository.save(bro);
	}
	public Veterinarian findOne(final int veterinarianId) {
		Veterinarian c;

		Assert.notNull(veterinarianId);
		Assert.isTrue(veterinarianId != 0);
		c = this.veterinarianRepository.findOne(veterinarianId);

		Assert.notNull(c);
		return c;
	}

	public Veterinarian reconstruct(final VeterinarianForm veterinarianForm, final BindingResult binding) {
		final Veterinarian veterinarian = this.create();

		//Assert.isTrue(VeterinarianForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos del veterinario con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.VETERINARIAN);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = veterinarianForm.getUserAccount();
		ua.setAuthorities(aus);

		veterinarian.setAddress(veterinarianForm.getAddress());
		veterinarian.setEmail(veterinarianForm.getEmail());
		veterinarian.setName(veterinarianForm.getName());
		veterinarian.setPhoneNumber(veterinarianForm.getPhoneNumber());
		veterinarian.setPhoto(veterinarianForm.getPhoto());
		veterinarian.setSurname(veterinarianForm.getSurname());
		veterinarian.setUserAccount(ua);
		veterinarian.setDescription(veterinarianForm.getDescription());

		veterinarian.setHolderName(veterinarianForm.getHolderName());
		veterinarian.setMakeName(veterinarianForm.getMakeName());
		veterinarian.setNumber(veterinarianForm.getNumber());
		veterinarian.setExpirationMonth(veterinarianForm.getExpirationMonth());
		veterinarian.setExpirationYear(veterinarianForm.getExpirationYear());
		veterinarian.setCvv(veterinarianForm.getCvv());

		this.validator.validate(veterinarian, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return veterinarian;
	}


	@Autowired
	private Validator	validator;


	public Veterinarian reconstruct(final Veterinarian veterinarian, final BindingResult binding) {
		Veterinarian res;

		//Check authority
		final Authority a = new Authority();
		final Authority b = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.ADMIN);
		b.setAuthority(Authority.VETERINARIAN);
		Assert.isTrue((user.getAuthorities().contains(a) || user.getAuthorities().contains(b)) && user.getAuthorities().size() == 1);

		if (veterinarian.getId() == 0)
			res = veterinarian;
		else
			res = this.veterinarianRepository.findOne(veterinarian.getId());
		res.setName(veterinarian.getName());
		res.setEmail(veterinarian.getEmail());
		res.setSurname(veterinarian.getSurname());
		res.setAddress(veterinarian.getAddress());
		res.setPhoneNumber(veterinarian.getPhoneNumber());
		res.setPhoto(veterinarian.getPhoto());
		res.setDescription(veterinarian.getDescription());

		res.setHolderName(veterinarian.getHolderName());
		res.setMakeName(veterinarian.getMakeName());
		res.setNumber(veterinarian.getNumber());
		res.setExpirationMonth(veterinarian.getExpirationMonth());
		res.setExpirationYear(veterinarian.getExpirationYear());
		res.setCvv(veterinarian.getCvv());

		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void leave() {
		final Veterinarian logVeterinarian = this.findByPrincipal();

		logVeterinarian.setAddress("Unknown");
		logVeterinarian.setEmail("unknown@unknown.com");
		logVeterinarian.setName("Unknown");
		logVeterinarian.setPhoneNumber("Unknown");
		logVeterinarian.setPhoto("http://www.unknown.com");

		logVeterinarian.setSocialProfiles(null);
		logVeterinarian.setSurname("Unknown");

		logVeterinarian.setHolderName("Unknown");
		logVeterinarian.setMakeName("Unknown");
		logVeterinarian.setCvv(123);
		logVeterinarian.setExpirationMonth(1);
		logVeterinarian.setExpirationYear(9999);
		logVeterinarian.setNumber("4532134223318979");
		logVeterinarian.setDescription("Unknown");

		final UserAccount ua = logVeterinarian.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		//TODO: DESCOMENTAR
		//		if (logVeterinarian.getSocialProfiles() != null)
		//			for (final SocialProfile sp : logVeterinarian.getSocialProfiles())
		//				this.socialprofileService.deleteLeave(sp);
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logVeterinarian.setUserAccount(ua);
	}

	public void flush() {
		this.veterinarianRepository.flush();
	}

}
