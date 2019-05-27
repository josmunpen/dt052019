
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PetType;

@Component
@Transactional
public class PetTypeToStringConverter implements Converter<PetType, String> {

	@Override
	public String convert(final PetType a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
