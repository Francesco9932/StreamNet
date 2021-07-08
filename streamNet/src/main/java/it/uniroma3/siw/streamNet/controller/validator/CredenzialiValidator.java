package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Credenziali;
import it.uniroma3.siw.streamNet.model.Utente;
import it.uniroma3.siw.streamNet.service.CredenzialiService;

@Component
public class CredenzialiValidator implements Validator {
    @Autowired
    private CredenzialiService credenzialiService;
    
    final Integer MAX_PASSWORD_LUN = 20;
    final Integer MIN_PASSWORD_LUN = 4;
    final Integer MAX_USERNAME_LUN = 20;
    final Integer MIN_USERNAME_LUN = 2;
    
    @Override
    public void validate(Object o, Errors errors) {
    	
    	Credenziali credenziali = (Credenziali) o;
    	
    	String username = credenziali.getUsername().trim();
    	String password  =credenziali.getPassword().trim();
    	
    	if (username.isEmpty())
            errors.rejectValue("username", "required");
    	 else if (username.length() < MIN_PASSWORD_LUN || username.length() > MAX_PASSWORD_LUN)
             errors.rejectValue("username", "size");
        else if (this.credenzialiService.getCredenziali(username) != null)
            errors.rejectValue("username", "duplicate");

        if (password.isEmpty())
            errors.rejectValue("password", "required");
        else if (password.length() < MIN_PASSWORD_LUN || password.length() > MAX_PASSWORD_LUN)
            errors.rejectValue("password", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Utente.class.equals(clazz);
    }   
}
