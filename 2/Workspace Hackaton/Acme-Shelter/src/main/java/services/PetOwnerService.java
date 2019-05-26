
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

import repositories.PetOwnerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.TickerGenerator;
import domain.Actor;
import domain.PetOwner;
import domain.SocialProfile;
import forms.PetOwnerForm;

@Service
@Transactional
public class PetOwnerService {

	@Autowired
	public PetOwnerRepository	petOwnerRepository;

	@Autowired
	public ActorService2		actorService;


	//TODO: DESCOMENTAR
	//	@Autowired
	//	public SocialProfileService	socialprofileService;

	//Constructor
	public PetOwnerService() {
		super();
	}

	public PetOwner create() {

		PetOwner result;
		result = new PetOwner();

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

		// PetOwner

		return result;
	}

	public PetOwner findByPrincipal() {
		PetOwner res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public PetOwner findOnePrincipal() {
		final Actor a = this.actorService.findByPrincipal();
		return this.petOwnerRepository.findOne(a.getId());
	}

	public PetOwner findOne(final PetOwner PetOwner) {
		return this.petOwnerRepository.findOne(PetOwner.getId());
	}

	public Collection<PetOwner> findAll() {
		return this.petOwnerRepository.findAll();
	}

	public PetOwner save(final PetOwner PetOwner) {
		Assert.notNull(PetOwner);

		final String pnumber = PetOwner.getPhoneNumber();
		//TODO: DESCCOMENTAR
		//		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		//		final String cc = cus.getPhoneNumberCode();
		//		if (pnumber.matches("^[0-9]{4,}$"))
		//			PetOwner.setPhoneNumber(cc.concat(pnumber));

		if (PetOwner.getId() != 0) {
			Assert.isTrue(this.actorService.checkPetOwner());

			// Modified PetOwner must be logged PetOwner
			final PetOwner logPetOwner;
			logPetOwner = this.findByPrincipal();
			Assert.notNull(logPetOwner);
			Assert.notNull(logPetOwner.getId());

		} else {

			//TODO: DESCOMENTAR
			//			final Collection<Box> boxes = this.actorService.createPredefinedBoxes();
			//			PetOwner.setBoxes(boxes);
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = PetOwner.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = PetOwner.getUserAccount();
			cuenta.setPassword(hash);
			PetOwner.setUserAccount(cuenta);
		}
		// Restrictions
		PetOwner res;

		res = this.petOwnerRepository.save(PetOwner);
		return res;
	}

	public PetOwner findByUserAccount(final UserAccount userAccount) {
		PetOwner res;
		Assert.notNull(userAccount);

		res = this.petOwnerRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public PetOwner findOne(final int PetOwnerId) {
		PetOwner c;

		Assert.notNull(PetOwnerId);
		Assert.isTrue(PetOwnerId != 0);
		c = this.petOwnerRepository.findOne(PetOwnerId);

		Assert.notNull(c);
		return c;
	}

	public PetOwner reconstruct(final PetOwnerForm petOwnerForm, final BindingResult binding) {
		final PetOwner petOwner = this.create();

		//Assert.isTrue(PetOwnerForm.getUserAccount().getAuthorities() == colMem);
		//Damos valores a los atributos de la hermandad con los datos que nos llegan
		final Authority com = new Authority();
		com.setAuthority(Authority.ADOPTER);
		final List<Authority> aus = new ArrayList<>();
		aus.add(com);
		final UserAccount ua = petOwnerForm.getUserAccount();
		ua.setAuthorities(aus);

		petOwner.setAddress(petOwnerForm.getAddress());
		petOwner.setEmail(petOwnerForm.getEmail());
		petOwner.setName(petOwnerForm.getName());
		petOwner.setPhoneNumber(petOwnerForm.getPhoneNumber());
		petOwner.setPhoto(petOwnerForm.getPhoto());
		petOwner.setSurname(petOwnerForm.getSurname());
		petOwner.setDescription(petOwnerForm.getDescription());

		petOwner.setUserAccount(ua);
		petOwner.setHolderName(petOwnerForm.getHolderName());
		petOwner.setMakeName(petOwnerForm.getMakeName());
		petOwner.setNumber(petOwnerForm.getNumber());
		petOwner.setExpirationMonth(petOwnerForm.getExpirationMonth());
		petOwner.setExpirationYear(petOwnerForm.getExpirationYear());
		petOwner.setCvv(petOwnerForm.getCvv());

		this.validator.validate(petOwner, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return petOwner;
	}


	@Autowired
	private Validator	validator;


	public PetOwner reconstruct(final PetOwner petOwner, final BindingResult binding) {
		PetOwner res;

		//Check authority
		final Authority a = new Authority();
		final Actor act = this.actorService.findByPrincipal();
		final UserAccount user = act.getUserAccount();
		a.setAuthority(Authority.PETOWNER);
		Assert.isTrue(user.getAuthorities().contains(a) && user.getAuthorities().size() == 1);

		if (petOwner.getId() == 0)
			res = petOwner;
		else
			res = this.petOwnerRepository.findOne(petOwner.getId());
		res.setName(petOwner.getName());
		res.setEmail(petOwner.getEmail());
		res.setSurname(petOwner.getSurname());
		res.setAddress(petOwner.getAddress());
		res.setPhoneNumber(petOwner.getPhoneNumber());
		res.setPhoto(petOwner.getPhoto());

		res.setHolderName(petOwner.getHolderName());
		res.setMakeName(petOwner.getMakeName());
		res.setNumber(petOwner.getNumber());
		res.setExpirationMonth(petOwner.getExpirationMonth());
		res.setExpirationYear(petOwner.getExpirationYear());
		res.setCvv(petOwner.getCvv());
		res.setDescription(petOwner.getDescription());
		this.validator.validate(res, binding);
		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public void leave() {
		final PetOwner logPetOwner = this.findByPrincipal();

		logPetOwner.setAddress("Unknown");
		logPetOwner.setEmail("unknown@unknown.com");
		logPetOwner.setName("Unknown");
		logPetOwner.setPhoneNumber("Unknown");
		logPetOwner.setPhoto("http://www.unknown.com");
		logPetOwner.setSocialProfiles(null);
		logPetOwner.setSurname("Unknown");
		logPetOwner.setDescription("Unknown");

		logPetOwner.setHolderName("Unknown");
		logPetOwner.setMakeName("Unknown");
		logPetOwner.setCvv(123);
		logPetOwner.setExpirationMonth(1);
		logPetOwner.setExpirationYear(9999);
		logPetOwner.setNumber("4532134223318979");

		final UserAccount ua = logPetOwner.getUserAccount();
		final String tick1 = TickerGenerator.tickerLeave();
		//TODO: DESCOMENTAR
		//		if (logPetOwner.getSocialProfiles() != null)
		//			for (final SocialProfile sp : logPetOwner.getSocialProfiles())
		//				this.socialprofileService.deleteLeave(sp);
		ua.setUsername("Unknown" + tick1);
		final String pass1 = TickerGenerator.generateTicker();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String pass2 = encoder.encodePassword(pass1, null);
		ua.setPassword(pass2);
		logPetOwner.setUserAccount(ua);
	}

	public void flush() {
		this.petOwnerRepository.flush();
	}

}
