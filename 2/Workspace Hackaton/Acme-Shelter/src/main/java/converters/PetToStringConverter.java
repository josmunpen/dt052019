
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Pet;

@Component
@Transactional
class PetToStringConverter implements Converter<Pet, String> {

	@Override
	public String convert(final Pet a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
