package es.uji.curso.test.integration;

import static org.mockito.Mockito.*; 

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.contactlist.ContactList;
import es.uji.curso.contactlist.ContactListFactory;
import es.uji.curso.contactlist.exceptions.NotValidPhoneException;
import es.uji.curso.contactlist.personphone.PersonPhoneRelation;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.contactlist.validation.PhoneValidator;
import es.uji.curso.test.TestUtils;

public class ContactListValidationTest {
	
	private static final String PHONE_NUMBER_WITH_ALFA_CHAR = "123A56789";
	
	private ContactListValidator validator;
	private ContactList contactList;
	private ContactListFactory factory;

	@Before
	public void setUp() throws IOException {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);
		factory = mock(ContactListFactory.class);
		validator = new PhoneValidator();
		
		contactList = new ContactList(factory, validator, null);		

		when(factory.createPersonPhoneRelationFor(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1)).thenReturn(
				personPhoneRelation);
		
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
