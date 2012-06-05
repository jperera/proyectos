package es.uji.curso.contactlist;

import java.io.IOException;

public class ContactList {

	private ContactListFactory factory;
	private ContactListQuery query;

	public ContactList(ContactListFactory factory, ContactListQuery query) {
		this.factory = factory;
		this.query = query;
	}

	public void assignPhoneToPerson(String phone, int person) throws IOException {
		PersonPhoneRelation personPhoneRelation = factory.createPersonPhoneRelationFor(phone, person);
		personPhoneRelation.store();
	}

	public Object getPhoneById(int i) throws IOException {
		return query.getPhonesById(i);
	}
	
}
