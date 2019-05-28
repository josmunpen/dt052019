
package forms;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;
import domain.Box;
import domain.Curriculum;
import domain.SocialProfile;

public class VeterinarianForm {

	private String						name;
	private String						surname;
	private String						photo;
	private String						email;
	private String						phoneNumber;
	private String						address;
	private Collection<SocialProfile>	socialProfiles;
	private Collection<Box>				boxes;
	private UserAccount					userAccount;
	private String						description;

	private String						holderName;
	private String						makeName;
	private String						number;
	private Integer						expirationYear;
	private Integer						expirationMonth;
	private Integer						cvv;

	private boolean						conditionsAccepted;


	@OneToMany
	public Collection<Box> getBoxes() {
		return this.boxes;
	}

	public void setBoxes(final Collection<Box> boxes) {
		this.boxes = boxes;
	}

	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotBlank
	@SafeHtml
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	@SafeHtml
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@SafeHtml
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<SocialProfile> getSocialProfiles() {
		return this.socialProfiles;
	}

	public void setSocialProfiles(final Collection<SocialProfile> socialProfiles) {
		this.socialProfiles = socialProfiles;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@NotBlank
	@SafeHtml
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	@SafeHtml
	public String getMakeName() {
		return this.makeName;
	}

	public void setMakeName(final String makeName) {
		this.makeName = makeName;
	}
	@NotBlank
	@CreditCardNumber
	@Pattern(regexp = "^[0-9]+$")
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}
	@NotNull
	@Min(2019)
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@NotNull
	@Range(min = 1, max = 12)
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@NotNull
	@Range(min = 0, max = 999)
	public Integer getCvv() {
		return this.cvv;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	public boolean isConditionsAccepted() {
		return this.conditionsAccepted;
	}

	public void setConditionsAccepted(final boolean conditionsAccepted) {
		this.conditionsAccepted = conditionsAccepted;
	}


	private Collection<Curriculum>	curriculums;


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Curriculum> getCurriculums() {
		return this.curriculums;
	}

	public void setCurriculums(final Collection<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
}
