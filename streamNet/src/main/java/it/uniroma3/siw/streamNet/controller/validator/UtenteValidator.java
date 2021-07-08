package it.uniroma3.siw.streamNet.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.streamNet.model.Utente;

@Component
public class UtenteValidator implements Validator{

	final Integer MAX_LUN = 100;
	final Integer MIN_LUN = 4;

	@Override
	public void validate(Object o, Errors errors) {

		Utente utente = (Utente) o;
		String nome = utente.getNome().trim();
		String cognome = utente.getCognome().trim();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if (nome.length() < MIN_LUN || nome.length() > MAX_LUN)
			errors.rejectValue("nome", "size");

		if (cognome.isEmpty())
			errors.rejectValue("cognome", "required");
		else if (cognome.length() < MIN_LUN || cognome.length() > MAX_LUN)
			errors.rejectValue("cognome", "size");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.equals(clazz);
	}
}


