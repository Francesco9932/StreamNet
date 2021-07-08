package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Film;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Component
public class FilmValidator implements Validator{

	@Autowired
	private StreamNetService streamNetService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Film.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");

		if (!errors.hasErrors()) {
			if (this.streamNetService.filmAlreadyExist((Film)o)) {
				errors.reject("duplicatoCuratore");
			}
		}
	}

}
