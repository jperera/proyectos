package es.uji.curso.test.integration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;
import es.uji.curso.contactlist.personphone.PersonPhoneRelation;
import es.uji.curso.test.TestUtils;

public class PersonPhoneRelationPersistenceTest {

	@Test
	public void testDoubleInsertionAndRetrieveRelation() throws IOException {
		ContactListFilePersistence persistence = new ContactListFilePersistence(TestUtils.FILE_FULL_PATH_SAMPLE);
		PersonPhoneRelation relationPerson1Phone1 = new PersonPhoneRelation(TestUtils.TEST_PERSON1_PHONE1_RELATION_LINE, persistence);
		PersonPhoneRelation relationPerson1Phone2 = new PersonPhoneRelation(TestUtils.TEST_PERSON1_PHONE2_RELATION_LINE, persistence);
	
		relationPerson1Phone1.store();
		relationPerson1Phone2.store();
		
		List<String> lines = persistence.readLines();

		Assert.assertEquals(TestUtils.TEST_PERSON1_PHONE1_RELATION_LINE, lines.get(0));
		Assert.assertEquals(TestUtils.TEST_PERSON1_PHONE2_RELATION_LINE, lines.get(1));
	}

	@After
	public void tearDown() {
		File file = new File(TestUtils.FILE_FULL_PATH_SAMPLE);
		file.delete();
	}
}
