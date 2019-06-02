
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Tip extends DomainEntity {

	private Date	moment;
	private String	title;
	private String	body;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml
	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}


	private Collection<PetType>	petTypes;
	private Veterinarian		veterinarian;


	@ManyToMany
	public Collection<PetType> getPetTypes() {
		return this.petTypes;
	}

	public void setPetTypes(final Collection<PetType> petTypes) {
		this.petTypes = petTypes;
	}

	@Valid
	@ManyToOne(optional = false)
	public Veterinarian getVeterinarian() {
		return this.veterinarian;
	}

	public void setVeterinarian(final Veterinarian veterinarian) {
		this.veterinarian = veterinarian;
	}

}
