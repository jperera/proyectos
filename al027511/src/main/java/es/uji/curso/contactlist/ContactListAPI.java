package es.uji.curso.contactlist;

import java.io.IOException;

public class ContactListAPI {
	
	private static ContactList contactList;
	
	static {
		ContactListFactory factory = new ContactListFactory();
		contactList = factory.createContactList();
	}

	public static void assignPhoneToPerson(String phone, int person) throws IOException {
		contactList.assignPhoneToPerson(phone, person);
	}

	public static Object getPhoneById(int i) throws IOException {
		return contactList.getPhoneById(i);
	}

}
