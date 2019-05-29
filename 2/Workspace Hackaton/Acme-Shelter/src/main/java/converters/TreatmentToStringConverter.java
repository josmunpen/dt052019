
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Treatment;

@Component
@Transactional
public class TreatmentToStringConverter implements Converter<Treatment, String> {

	@Override
	public String convert(final Treatment a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
