
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class PetType extends DomainEntity {

	private String	name;
	private String	nombre;
	private String	scientificTerm;
	private String	description;
	private String	zone;
	private boolean	finalMode;


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
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	@SafeHtml
	public String getScientificTerm() {
		return this.scientificTerm;
	}

	public void setScientificTerm(final String scientificTerm) {
		this.scientificTerm = scientificTerm;
	}

	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@SafeHtml
	public String getZone() {
		return this.zone;
	}

	public void setZone(final String zone) {
		this.zone = zone;
	}

	public boolean isFinalMode() {
		return this.finalMode;
	}

	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}


	private Collection<PetType>	childs;


	@OneToMany
	public Collection<PetType> getChilds() {
		return this.childs;
	}

	public void setChilds(final Collection<PetType> childs) {
		this.childs = childs;
	}

}
