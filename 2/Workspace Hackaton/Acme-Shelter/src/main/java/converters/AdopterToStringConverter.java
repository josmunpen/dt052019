package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Adopter;


public class AdopterToStringConverter implements Converter<Adopter, String>{

	@Override
	public String convert(final Adopter m) {
		String result;

		if (m == null)
			result = null;
		else
			result = String.valueOf(m.getId());
		return result;
	}

}
