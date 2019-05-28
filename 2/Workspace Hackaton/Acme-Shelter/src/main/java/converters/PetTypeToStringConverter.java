
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PetType;

@Component
@Transactional
public class PetTypeToStringConverter implements Converter<PetType, String> {

	@Override
	public String convert(final PetType c) {
		String result;

		if (c == null)
			result = null;
		else
			result = c.getName().toString();

		return result;
	}

}
