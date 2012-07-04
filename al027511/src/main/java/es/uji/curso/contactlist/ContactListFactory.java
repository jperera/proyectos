package es.uji.curso.contactlist;

import java.io.*;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;
import es.uji.curso.contactlist.personphone.*;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.contactlist.validation.PhoneValidator;

public class ContactListFactory {

	public PersonPhoneRelation createPersonPhoneRelationFor(String phone, int person) throws IOException {
		return new PersonPhoneRelation(person, phone, getPersistence());
	}

	public PersonPhoneRelation createPersonPhoneRelationFrom(String line) throws IOException {
		return new PersonPhoneRelation(line, getPersistence());
	}

	public ContactListFilePersistence getPersistence() throws FileNotFoundException {
		return new ContactListFilePersistence("contact_list_test.data");
	}

	public PersonPhoneRelationFinder createPersonPhoneRelationFinder() {
		try {
			return new PersonPhoneRelationFinderImpl(this, getPersistence());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ContactList createContactList() {
		return new ContactList(this, new PhoneValidator(), createPersonPhoneRelationFinder());
	}

}
