
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Treatment extends DomainEntity {

	private Date	moment;
	private String	illness;
	private String	treatmentC;
	private String	comment;


	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@SafeHtml
	@NotBlank
	public String getIllness() {
		return this.illness;
	}

	public void setIllness(final String illness) {
		this.illness = illness;
	}

	@SafeHtml
	@NotBlank
	public String getTreatmentC() {
		return this.treatmentC;
	}

	public void setTreatmentC(final String treatment) {
		this.treatmentC = treatment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

}
