
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.MedicalCheckUpRepository;
import domain.MedicalCheckUp;

@Component
@Transactional
public class StringToMedicalCheckUpConverter implements Converter<String, MedicalCheckUp> {

	@Autowired
	MedicalCheckUpRepository	mr;


	@Override
	public MedicalCheckUp convert(final String text) {
		MedicalCheckUp result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.mr.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;

	}
}
