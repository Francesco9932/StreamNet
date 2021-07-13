package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.SerieTv;
import it.uniroma3.siw.streamNet.service.StreamNetService;

@Component
public class SerieTvValidator implements Validator{

	@Autowired
	private StreamNetService streamNetService;

	@Override
	public boolean supports(Class<?> clazz) {
		return SerieTv.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genere", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attori", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "annoDiUscita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lingua", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "immagine", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "registaSerie", "required");

		if (!errors.hasErrors()) {
			if (this.streamNetService.serieAlreadyExist((SerieTv)o)) {
				errors.reject("duplicatoSerie");
			}
		}
	}
}
