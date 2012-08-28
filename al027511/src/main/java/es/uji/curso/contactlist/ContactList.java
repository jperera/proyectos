package es.uji.curso.contactlist;

import java.io.IOException;
import java.util.List;

import es.uji.curso.contactlist.exceptions.NotValidPhoneException;
import es.uji.curso.contactlist.persistence.PersonPhoneRelationStorer;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinder;
import es.uji.curso.contactlist.personphone.PhoneNumber;
import es.uji.curso.contactlist.validation.ContactListValidator;

public class ContactList {

	private PersonPhoneRelationStorer writer;
	private ContactListValidator validator;
	private PersonPhoneRelationFinder personPhoneRelationFinder;
	private ContactListFactory factory = new ContactListFactory();

	public ContactList(PersonPhoneRelationStorer writer, ContactListValidator validator, PersonPhoneRelationFinder personPhoneRelationFinder) {
		this.writer = writer;
		this.validator = validator;
		this.personPhoneRelationFinder = personPhoneRelationFinder;
	}

	public void assignPhoneToPerson(String inputPhoneNumber, int personId) throws IOException {
		if (isInvalid(inputPhoneNumber)) {
			throw new NotValidPhoneException();
		}
		PhoneNumber phoneNumber = factory.getPhoneNumberFrom(inputPhoneNumber);
		writer.storeRelationOf(personId, phoneNumber.getId());
	}

	private boolean isInvalid(String phoneNumber) {
		return !validator.isValid(phoneNumber);
	}

	public List<String> getPhoneById(int personId) throws IOException {
		return personPhoneRelationFinder.findPhonesByPersonId(personId);
		
	}

}
