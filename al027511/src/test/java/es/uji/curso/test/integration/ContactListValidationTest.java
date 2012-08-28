package es.uji.curso.test.integration;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.uji.curso.contactlist.ContactList;
import es.uji.curso.contactlist.exceptions.NotValidPhoneException;
import es.uji.curso.contactlist.persistence.PersonPhoneRelationStorer;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.contactlist.validation.PhoneValidator;
import es.uji.curso.test.TestUtils;

public class ContactListValidationTest {
	
	private static final String PHONE_NUMBER_WITH_ALFA_CHAR = "123A56789";
	
	private ContactListValidator validator;
	private ContactList contactList;
	private PersonPhoneRelationStorer writer;

	@Before
	public void setUp() throws IOException {
		validator = new PhoneValidator();
		writer = Mockito.mock(PersonPhoneRelationStorer.class);
		contactList = new ContactList(writer, validator, null);
	}
	
	@Test(expected=NotValidPhoneException.class)
	public void testNotValidPhone() throws IOException {
		contactList.assignPhoneToPerson(PHONE_NUMBER_WITH_ALFA_CHAR, TestUtils.TEST_PERSON_ID1);
	}
	
	@Test
	public void testValidPhone() throws IOException {
		contactList.assignPhoneToPerson(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1);
	}

}
