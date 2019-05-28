
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ActorRepository;
import services.AdministratorService;
import services.AdopterService;
import services.PetOwnerService;
import services.VeterinarianService;
import domain.Actor;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	private ActorRepository			ar;

	@Autowired
	private AdministratorService	as;

	@Autowired
	private VeterinarianService		vs;

	@Autowired
	private PetOwnerService			pos;

	@Autowired
	private AdopterService			ads;


	@Override
	public Actor convert(final String text) {
		Actor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.ar.findOne(id);
				if (result == null)
					result = this.as.findOne(id);
				if (result == null)
					result = this.vs.findOne(id);
				if (result == null)
					result = this.pos.findOne(id);
				if (result == null)
					result = this.ads.findOne(id);

			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
