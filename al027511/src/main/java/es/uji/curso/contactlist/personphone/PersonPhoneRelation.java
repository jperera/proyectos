package es.uji.curso.contactlist.personphone;

import java.io.IOException;
import java.io.Writer;

import es.uji.curso.contactlist.persistence.LineWriter;

public class PersonPhoneRelation {

	private static final int PERSON_ID_POSITION_IN_PERSON_PHONE_RELATION = 0;
	private static final int PHONE_POSITION_IN_PERSON_PHONE_RELATION = 1;
	private static final String LINE_ELEMENT_SEPARATOR = " ";

	private int personId;
	private String phone;
	private LineWriter writer;
	
	public PersonPhoneRelation(String personPhoneRelation, Writer writer) {
		this.personId = getPersonIdIn(personPhoneRelation);
		this.phone = getPhoneIn(personPhoneRelation);
	}

	public PersonPhoneRelation(int person, String phone, LineWriter writer) {
		this.personId = person;
		this.phone = phone;
		this.writer = writer;
	}

	public boolean isPerson(int person) {
		return personId == person;
	}

	public String getPhone() {
		return phone;
	}

	public void store() throws IOException {
		writer.write(personId + " " + phone + "\n");
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
