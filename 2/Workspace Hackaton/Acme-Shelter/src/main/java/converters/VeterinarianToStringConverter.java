
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Veterinarian;

@Component
@Transactional
class VeterinarianToStringConverter implements Converter<Veterinarian, String> {

	@Override
	public String convert(final Veterinarian a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
