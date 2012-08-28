package es.uji.curso.contactlist;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ContactListAPI {

	private static ContactList contactList;

	static {
		ContactListFactory factory = new ContactListFactory();
		try {
			contactList = factory.createContactList();
		} catch (FileNotFoundException e) {
			contactList = null;
		}

	}

	public static void assignPhoneToPerson(String phone, int person) throws IOException {
		contactList.assignPhoneToPerson(phone, person);
	}

	public static List<String> getPhonesById(int personId) throws IOException {
		return contactList.getPhoneById(personId);
	}

}
