
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.MedicalCheckUp;

@Component
@Transactional
public class MedicalCheckUpToStringConverter implements Converter<MedicalCheckUp, String> {

	@Override
	public String convert(final MedicalCheckUp a) {
		String result;

		if (a == null)
			result = null;
		else
			result = String.valueOf(a.getId());

		return result;
	}

}
