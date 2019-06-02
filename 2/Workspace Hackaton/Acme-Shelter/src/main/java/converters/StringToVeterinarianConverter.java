
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.VeterinarianRepository;
import domain.Veterinarian;

@Component
@Transactional
public class StringToVeterinarianConverter implements Converter<String, Veterinarian> {

	@Autowired
	VeterinarianRepository	pr;


	@Override
	public Veterinarian convert(final String text) {
		Veterinarian result;
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
