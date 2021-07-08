package it.uniroma3.siw.streamNet.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Regista;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Component
public class RegistaValidator implements Validator{
	
	@Autowired 
	private StreamNetService streamNetService;

	@Override
	public void validate(Object o, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome" , "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome" , "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita" , "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "film" , "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serieTv" , "required");
		
		if(!errors.hasErrors()) {
			if(this.streamNetService.registaAlreadyExist((Regista)o)) {
				errors.reject("duplicato");
			}
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		
		return Regista.class.equals(aClass);
	}
	

}
