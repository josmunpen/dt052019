
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.PetOwner;

@Component
@Transactional
class PetOwnerToStringConverter implements Converter<PetOwner, String> {

	@Override
	public String convert(final PetOwner a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
