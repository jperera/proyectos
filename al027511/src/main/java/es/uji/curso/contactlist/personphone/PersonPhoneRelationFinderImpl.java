package es.uji.curso.contactlist.personphone;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uji.curso.contactlist.ContactListFactory;

public class PersonPhoneRelationFinderImpl implements PersonPhoneRelationFinder {

	private ContactListFactory factory;
	private BufferedReader reader;

	public PersonPhoneRelationFinderImpl(ContactListFactory factory, BufferedReader reader) {
		this.factory = factory;
		this.reader = reader;
	}

	public List<String> findPhonesByPersonId(int personId) throws IOException {
		List<String> phones = new ArrayList<String>();
		String personPhoneRawLine = getNextLine(reader);

		while (isProcesableLine(personPhoneRawLine)) {
			PersonPhoneRelation personPhoneRelation = createFromRawLine(factory, personPhoneRawLine);
			addPhoneToListWithPersonFilter(personPhoneRelation, phones, personId);
			personPhoneRawLine = getNextLine(reader);
		}

		return phones;
	}

	private String getNextLine(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	private boolean isProcesableLine(String personPhoneRawLine) {
		return personPhoneRawLine != null;
	}

	private PersonPhoneRelation createFromRawLine(ContactListFactory factory, String personPhoneRawLine)
			throws IOException {
		return factory.createPersonPhoneRelationFrom(personPhoneRawLine);
	}

	private void addPhoneToListWithPersonFilter(PersonPhoneRelation personPhoneRelation, List<String> phones,
			int personId) {
		if (personPhoneRelation.isPerson(personId)) {
			phones.add(personPhoneRelation.getPhone());
		}
	}

}
