package es.uji.curso.contactlist.personphone;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.uji.curso.contactlist.persistence.LineWriter;

public class PersonPhoneRelationTest {

	private static final int TEST_PERSON_ID = 1;
	private static final String TEST_PHONE = "964000000";

	private LineWriter writer;

	@Before
	public void setUp() {
		writer = Mockito.mock(LineWriter.class);
	}
	
	@Test
	public void testStore() throws IOException {
		PersonPhoneRelation personPhoneRelation = new PersonPhoneRelation(TEST_PERSON_ID, TEST_PHONE, writer);
		personPhoneRelation.store();
		Mockito.verify(writer).write(TEST_PERSON_ID + " " + TEST_PHONE + "\n");
	}

}
