
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.TreatmentRepository;
import domain.Treatment;

@Component
@Transactional
public class StringToTreatmentConverter implements Converter<String, Treatment> {

	@Autowired
	TreatmentRepository	pr;


	@Override
	public Treatment convert(final String text) {
		Treatment result;
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
