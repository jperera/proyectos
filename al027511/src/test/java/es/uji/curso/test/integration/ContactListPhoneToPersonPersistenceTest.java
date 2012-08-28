package es.uji.curso.test.integration;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import es.uji.curso.contactlist.ContactList;
import es.uji.curso.contactlist.persistence.ContactListFilePersistence;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.test.TestUtils;

public class ContactListPhoneToPersonPersistenceTest {

	@Test
	public void testDoubleInsertionAndRetrieveRelation() throws IOException {
		ContactListFilePersistence persistence = new ContactListFilePersistence(TestUtils.FILE_FULL_PATH_SAMPLE);
		ContactListValidator validator = mock(ContactListValidator.class);
		ContactList contactList = new ContactList(persistence, validator, null);

		when(validator.isValid(anyString())).thenReturn(true);

		contactList.assignPhoneToPerson(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1);
		contactList.assignPhoneToPerson(TestUtils.TEST_PHONE_NUMBER2, TestUtils.TEST_PERSON_ID1);

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
