package es.uji.curso.contactlist;

import java.io.FileNotFoundException;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;
import es.uji.curso.contactlist.personphone.*;
import es.uji.curso.contactlist.validation.PhoneValidator;

public class ContactListFactory {

	public ContactListFilePersistence getPersistence() throws FileNotFoundException {
		return new ContactListFilePersistence("contact_list_test.data");
	}

	public PersonPhoneRelationFinder createPersonPhoneRelationFinder() {
		try {
			return new PersonPhoneRelationFinderImpl(getPersistence());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public ContactList createContactList() throws FileNotFoundException {
		return new ContactList(getPersistence(), new PhoneValidator(), createPersonPhoneRelationFinder());
	}
	
	public PhoneNumber getPhoneNumberFrom(String inputPhoneNumber) {
		return new PhoneNumber(inputPhoneNumber);
	}

}
