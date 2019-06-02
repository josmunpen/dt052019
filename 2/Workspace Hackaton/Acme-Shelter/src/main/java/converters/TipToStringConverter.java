
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Tip;

@Component
@Transactional
public class TipToStringConverter implements Converter<Tip, String> {

	@Override
	public String convert(final Tip c) {
		String result;

		if (c == null)
			result = null;
		else
			result = String.valueOf(c.getId());

		return result;
	}

}
