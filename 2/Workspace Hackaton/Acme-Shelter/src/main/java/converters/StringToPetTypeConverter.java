
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.PetTypeRepository;
import domain.PetType;

@Component
@Transactional
public class StringToPetTypeConverter implements Converter<String, PetType> {

	@Autowired
	PetTypeRepository	pr;


	@Override
	public PetType convert(final String text) {
		PetType result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.pr.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;

	}

}
