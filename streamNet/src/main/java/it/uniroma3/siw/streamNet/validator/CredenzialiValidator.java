package it.uniroma3.siw.streamNet.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Credenziali;
import it.uniroma3.siw.streamNet.service.CredenzialiService;

@Component
public class CredenzialiValidator implements Validator {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	 final Integer NAME_MAX_LUN = 20;
	 final Integer NAME_MIN_LUN = 4;
	 final Integer PASS_MAX_LUN = 20;
	 final Integer PASS_MIN_LUN = 4;
	
	@Override
	public void validate(Object o, Errors errors) {
		
		Credenziali credenziali = (Credenziali) o;
		String username = credenziali.getUsername().trim();
		String password=  credenziali.getPassword().trim();
		
		if(username.isEmpty())
			errors.rejectValue("username", "required");
		else if( username.length() < NAME_MIN_LUN || username.length() > NAME_MAX_LUN)
			errors.rejectValue("username",  "size");
		else if (this.credenzialiService.getCredenziali(username) != null)
            errors.rejectValue("username", "duplicate");
		
		if(password.isEmpty())
			errors.rejectValue("password", "requires");
		else if( password.length() < PASS_MIN_LUN || password.length() > PASS_MAX_LUN)
			errors.rejectValue("password", "size");
		}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Credenziali.class.equals(aClass);
	}
	
	 

}
