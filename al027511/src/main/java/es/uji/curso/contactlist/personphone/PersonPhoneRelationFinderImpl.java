package es.uji.curso.contactlist.personphone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.uji.curso.contactlist.persistence.LinesReader;

public class PersonPhoneRelationFinderImpl implements PersonPhoneRelationFinder {

	private static final int PERSON_ID_POSITION_IN_PERSON_PHONE_RELATION = 0;
	private static final int PHONE_POSITION_IN_PERSON_PHONE_RELATION = 1;
	private static final String LINE_ELEMENT_SEPARATOR = " ";

	private LinesReader reader;

	public PersonPhoneRelationFinderImpl(LinesReader reader) {
		this.reader = reader;
	}

	public List<String> findPhonesByPersonId(int personId) throws IOException {
		List<String> phones = new ArrayList<String>();

		for (String personPhoneRawLine : reader.readLines()) {
			addPhoneToListWithPersonFilter(personPhoneRawLine, phones, personId);
		}

		return phones;
	}

	private void addPhoneToListWithPersonFilter(String personPhoneRawLine, List<String> phones, int personId) {
		if (getPersonIdIn(personPhoneRawLine) == personId) {
			phones.add(getPhoneIn(personPhoneRawLine));
		}
	}

	private String getPhoneIn(String personPhoneRelation) {
		String[] lineElements = personPhoneRelation.split(LINE_ELEMENT_SEPARATOR);
		return lineElements[PHONE_POSITION_IN_PERSON_PHONE_RELATION];
	}

	private int getPersonIdIn(String personPhoneRelation) {
		String[] lineElements = personPhoneRelation.split(LINE_ELEMENT_SEPARATOR);
		return Integer.parseInt(lineElements[PERSON_ID_POSITION_IN_PERSON_PHONE_RELATION]);
	}

}
