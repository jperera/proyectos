package es.uji.curso.contactlist;

import java.io.*;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;
import es.uji.curso.contactlist.personphone.*;

public class ContactListFactory {

	public PersonPhoneRelation createPersonPhoneRelationFor(String phone, int person) throws IOException {
		return new PersonPhoneRelation(person, phone, new ContactListFilePersistence("contact_list_test.data"));
	}

	public PersonPhoneRelation createPersonPhoneRelationFrom(String line) throws IOException {
		return new PersonPhoneRelation(line, new FileWriter("contact_list_test.data"));
	}

	public BufferedReader getContactListReader() throws FileNotFoundException {
		return new BufferedReader(new FileReader("contact_list_test.data"));
	}

	public PersonPhoneRelationFinder createPhoneRelationFinder() {
		try {
			return new PersonPhoneRelationFinderImpl(this, getContactListReader());
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
