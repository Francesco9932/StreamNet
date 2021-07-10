package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Episodio;

@Component
public class EpisodioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Episodio.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "durata", "required");

		}
	}

