package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.AdopterRepository;

import domain.Adopter;


public class StringToAdopterConverter implements Converter<String, Adopter>{
	
	@Autowired
	AdopterRepository	mr;


	@Override
	public Adopter convert(final String text) {
		Adopter result;
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
