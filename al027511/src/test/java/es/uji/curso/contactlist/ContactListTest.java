package es.uji.curso.contactlist;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static junit.framework.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.contactlist.personphone.PersonPhoneRelation;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinder;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.test.TestUtils;

public class ContactListTest {

	private ContactListFactory factory;
	private ContactListValidator validator;
	private PersonPhoneRelationFinder personPhoneRelationFinder;
	private ContactList contactList;
	private PersonPhoneRelation personPhoneRelation;

	@Before
	public void setUp() throws IOException {
		factory = mock(ContactListFactory.class);
		validator = mock(ContactListValidator.class);
		personPhoneRelationFinder = mock(PersonPhoneRelationFinder.class);
		contactList = new ContactList(factory, validator, personPhoneRelationFinder);
		personPhoneRelation = mock(PersonPhoneRelation.class);

		when(validator.isValid(anyString())).thenReturn(true);
		when(factory.createPersonPhoneRelationFor(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1)).thenReturn(
				personPhoneRelation);
	}

	@Test
	public void testCallStoreWhenAssignPhoneToPersonMethod() throws IOException {
		contactList.assignPhoneToPerson(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1);
		verify(personPhoneRelation).store();
	}

	@Test
	public void testReturnFinderReturnWhenGetPhonesById() throws IOException {
		List<String> phoneList = getPhoneListSample1();

		when(personPhoneRelationFinder.findPhonesByPersonId(TestUtils.TEST_PERSON_ID1)).thenReturn(phoneList);
		assertEquals(phoneList, contactList.getPhoneById(TestUtils.TEST_PERSON_ID1));
	}

	@Test
	public void testReturnFinderReturnWhenGetPhonesByIdWithOtherList() throws IOException {
		List<String> phoneList = getPhoneListSample2();

		when(personPhoneRelationFinder.findPhonesByPersonId(TestUtils.TEST_PERSON_ID1)).thenReturn(phoneList);
		assertEquals(phoneList, contactList.getPhoneById(TestUtils.TEST_PERSON_ID1));
	}

	private List<String> getPhoneListSample1() {
		List<String> phoneList = new ArrayList<String>();

		phoneList.add(TestUtils.TEST_PHONE_NUMBER1);
		phoneList.add(TestUtils.TEST_PHONE_NUMBER2);
		return phoneList;
	}

	private List<String> getPhoneListSample2() {
		List<String> phoneList = new ArrayList<String>();

		phoneList.add(TestUtils.TEST_PHONE_NUMBER2);
		phoneList.add(TestUtils.TEST_PHONE_NUMBER1);
		return phoneList;
	}

}
