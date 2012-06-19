package es.uji.curso.contactlist.personphone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uji.curso.contactlist.ContactListFactory;
import es.uji.curso.contactlist.persistence.LinesReader;

public class PersonPhoneRelationFinderImpl implements PersonPhoneRelationFinder {

	private ContactListFactory factory;
	private LinesReader reader;

	public PersonPhoneRelationFinderImpl(ContactListFactory factory, LinesReader reader) {
		this.factory = factory;
		this.reader = reader;
	}

	public List<String> findPhonesByPersonId(int personId) throws IOException {
		List<String> phones = new ArrayList<String>();

		for (String personPhoneRawLine : reader.readLines()) {
			PersonPhoneRelation personPhoneRelation = createFromRawLine(personPhoneRawLine);
			addPhoneToListWithPersonFilter(personPhoneRelation, phones, personId);
		}

		return phones;
	}

	private PersonPhoneRelation createFromRawLine(String personPhoneRawLine) throws IOException {
		return factory.createPersonPhoneRelationFrom(personPhoneRawLine);
	}

	private void addPhoneToListWithPersonFilter(PersonPhoneRelation personPhoneRelation, List<String> phones,
			int personId) {
		if (personPhoneRelation.isPerson(personId)) {
			phones.add(personPhoneRelation.getPhone());
		}
	}

}
