package es.uji.curso.contactlist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactListQueryImpl implements ContactListQuery {

	private ContactListFactory factory;
	private BufferedReader reader;

	public ContactListQueryImpl(ContactListFactory factory, BufferedReader reader) {
		this.factory = factory;
		this.reader = reader;
	}

	public List<String> getPhonesById(int id) throws IOException {
		List<String> phones = new ArrayList<String>();
		for (PersonPhoneRelation personPhoneRelation : getPersonPhoneRelations()) {
			addPhoneForIdToPhoneList(id, phones, personPhoneRelation);
		}
		return phones;
	}

	private void addPhoneForIdToPhoneList(int id, List<String> phones, PersonPhoneRelation personPhoneRelation) {
		if (personPhoneRelation.isPerson(id)) {
			String phone = personPhoneRelation.getPhone();
			phones.add(phone);
		}
	}

	private List<PersonPhoneRelation> getPersonPhoneRelations() throws IOException {
		List<PersonPhoneRelation> personPhoneRelations = new ArrayList<PersonPhoneRelation>();
		String line;
		while ((line = reader.readLine()) != null) {
			personPhoneRelations.add(factory.createPersonPhoneRelationFor(line));
		}
		return personPhoneRelations;
	}

}
