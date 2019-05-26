
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private String	qualifications;
	private String	professionalCareers;


	@NotBlank
	@SafeHtml
	public String getQualifications() {
		return this.qualifications;
	}

	public void setQualifications(final String qualifications) {
		this.qualifications = qualifications;
	}

	@SafeHtml
	public String getProfessionalCareers() {
		return this.professionalCareers;
	}

	public void setProfessionalCareers(final String professionalCareers) {
		this.professionalCareers = professionalCareers;
	}

}
