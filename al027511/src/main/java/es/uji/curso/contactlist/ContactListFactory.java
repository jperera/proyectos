package es.uji.curso.contactlist;

import java.io.*;

public class ContactListFactory {

	public PersonPhoneRelation createPersonPhoneRelationFor(String phone, int person) throws IOException {
		return new PersonPhoneRelation(person, phone, new FileWriter("contact_list_test.data"));
	}

	public PersonPhoneRelation createPersonPhoneRelationFor(String line) throws IOException {
		return new PersonPhoneRelation(line, new FileWriter("contact_list_test.data"));
	}
	
	public ContactListQuery createContactListQuery() {
		try {
			return new ContactListQueryImpl(this, new BufferedReader(new FileReader("contact_list_test.data")));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public ContactList createContactList() {
		return new ContactList(this, createContactListQuery());
	}
	
}
