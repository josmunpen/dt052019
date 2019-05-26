
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Customisation extends DomainEntity {

	private String	systemName;
	private String	bannerUrl;
	private String	welcomeMessageEng;
	private String	welcomeMessageEsp;
	private String	phoneNumberCode;
	private Integer	finderDuration;
	private Integer	resultsNumber;
	private Integer	vat;
	private Integer	flatRate;


	@Range(min = 0, max = 100)
	@NotNull
	public Integer getVat() {
		return this.vat;
	}

	public void setVat(final Integer vat) {
		this.vat = vat;
	}
	@Min(0)
	@NotNull
	public Integer getFlatRate() {
		return this.flatRate;
	}

	public void setFlatRate(final Integer flatRate) {
		this.flatRate = flatRate;
	}

	@NotBlank
	@SafeHtml
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	@NotBlank
	@URL
	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(final String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	@NotBlank
	@SafeHtml
	public String getWelcomeMessageEng() {
		return this.welcomeMessageEng;
	}

	public void setWelcomeMessageEng(final String welcomeMessageEng) {
		this.welcomeMessageEng = welcomeMessageEng;
	}
	@NotBlank
	@SafeHtml
	public String getWelcomeMessageEsp() {
		return this.welcomeMessageEsp;
	}

	public void setWelcomeMessageEsp(final String welcomeMessageEsp) {
		this.welcomeMessageEsp = welcomeMessageEsp;
	}
	@NotBlank
	@SafeHtml
	public String getPhoneNumberCode() {
		return this.phoneNumberCode;
	}

	public void setPhoneNumberCode(final String phoneNumberCode) {
		this.phoneNumberCode = phoneNumberCode;
	}

	@NotNull
	@Range(min = 1, max = 24)
	public Integer getFinderDuration() {
		return this.finderDuration;
	}

	public void setFinderDuration(final Integer finderDuration) {
		this.finderDuration = finderDuration;
	}
	@NotNull
	@Range(min = 10, max = 100)
	public Integer getResultsNumber() {
		return this.resultsNumber;
	}

	public void setResultsNumber(final Integer resultsNumber) {
		this.resultsNumber = resultsNumber;
	}

}
