
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class MedicalCheckUp extends DomainEntity {

	private Date	moment;
	private String	description;
	private String	stateOfHealth;


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
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^" + "CRITICAL" + "|" + "BAD" + "|" + "GOOD" + "|" + "EXCELENT" + "$")
	public String getStateOfHealth() {
		return this.stateOfHealth;
	}

	public void setStateOfHealth(final String stateOfHealth) {
		this.stateOfHealth = stateOfHealth;
	}


	private Veterinarian			veterinarian;
	private Pet					pet;
	private Collection<Treatment>	treatments;


	@Valid
	@ManyToOne(optional = false)
	public Veterinarian getVeterinarian() {
		return this.veterinarian;
	}

	public void setVeterinarian(final Veterinarian veterinarian) {
		this.veterinarian = veterinarian;
	}

	@Valid
	@OneToOne(optional = false)
	public Pet getPet() {
		return this.pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Treatment> getTreatments() {
		return this.treatments;
	}

	public void setTreatments(final Collection<Treatment> treatments) {
		this.treatments = treatments;
	}

}
