package converters;

import org.springframework.core.convert.converter.Converter;

import domain.History;


public class HistoryToStringConverter implements Converter<History, String>{
	
	@Override
	public String convert(final History m) {
		String result;

		if (m == null)
			result = null;
		else
			result = String.valueOf(m.getId());
		return result;
	}

}
