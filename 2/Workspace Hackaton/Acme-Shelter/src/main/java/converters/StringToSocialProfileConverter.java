package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import repositories.SocialProfileRepository;
import domain.SocialProfile;

@Component
@Transactional
public class StringToSocialProfileConverter implements Converter<String, SocialProfile>{
	
	
	@Autowired
	private SocialProfileRepository profileRepository;
	
	@Override
	public SocialProfile convert(String text) {
		SocialProfile result;
		int id;
		
		try {
			if (StringUtils.isEmpty(text)){
				result = null;
			}else{
				id = Integer.valueOf(text);
				result = this.profileRepository.findOne(id);
			}
		} catch (Throwable oops){
			throw new IllegalArgumentException(oops);
			
		}
		
		return result;
	}


}
