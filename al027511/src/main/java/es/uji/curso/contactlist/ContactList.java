package es.uji.curso.contactlist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uji.curso.contactlist.exceptions.NotValidPhoneException;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinder;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.test.TestUtils;

public class ContactList {

	private ContactListFactory factory;
	private ContactListValidator validator;
	private PersonPhoneRelationFinder personPhoneRelationFinder;

	public ContactList(ContactListFactory factory, ContactListValidator validator, PersonPhoneRelationFinder personPhoneRelationFinder) {
		this.factory = factory;
		this.validator = validator;
		this.personPhoneRelationFinder = personPhoneRelationFinder;
	}

	public void assignPhoneToPerson(String phoneNumber, int personId) throws IOException {
		if (isInvalid(phoneNumber)) {
			throw new NotValidPhoneException();
		}
		store(phoneNumber, personId);
	}

	private boolean isInvalid(String phoneNumber) {
		return !validator.isValid(phoneNumber);
	}

	private void store(String phoneNumber, int personId) throws IOException {
		factory.createPersonPhoneRelationFor(phoneNumber, personId).store();
	}

	public List<String> getPhoneById(int personId) throws IOException {
		return personPhoneRelationFinder.findPhonesByPersonId(personId);
		
	}

}
