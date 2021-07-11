package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Stagione;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Component
public class StagioneValidator implements Validator{

	@Autowired
	private StreamNetService streamNetService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Stagione.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "required");
		
		if (!errors.hasErrors()) {
			if (this.streamNetService.stagioneAlreadyExist((Stagione)o)) {
				errors.reject("duplicatoSerie");
			}
		}
	}
	}

