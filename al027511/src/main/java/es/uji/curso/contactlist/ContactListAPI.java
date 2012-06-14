package es.uji.curso.contactlist;

import java.io.IOException;
import java.util.List;

import es.uji.curso.contactlist.personphone.PersonPhoneRelation;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinder;

public class ContactListAPI {
	
	private static ContactListFactory factory;
	private static PersonPhoneRelationFinder personPhoneRelationFinder;
	
	static {
		factory = new ContactListFactory();
		personPhoneRelationFinder = factory.createPhoneRelationFinder();
	}

	public static void assignPhoneToPerson(String phone, int person) throws IOException {
		PersonPhoneRelation personPhoneRelation = factory.createPersonPhoneRelationFor(phone, person);
		personPhoneRelation.store();
	}

	public static List<String> getPhonesById(int personId) throws IOException {
		return personPhoneRelationFinder.findPhonesByPersonId(personId);
	}

}
