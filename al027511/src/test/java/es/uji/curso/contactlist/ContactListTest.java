package es.uji.curso.contactlist;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.contactlist.persistence.PersonPhoneRelationStorer;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinder;
import es.uji.curso.contactlist.validation.ContactListValidator;
import es.uji.curso.test.TestUtils;

public class ContactListTest {
	
	private static final String PHONE_NUMBER_WITH_3_2_2_2_GROUPS = "123 45 67 89";
	
	private ContactListValidator validator;
	private PersonPhoneRelationFinder personPhoneRelationFinder;
	private ContactList contactList;
	private PersonPhoneRelationStorer writer;

	@Before
	public void setUp() throws IOException {
		writer = mock(PersonPhoneRelationStorer.class);
		validator = mock(ContactListValidator.class);
		personPhoneRelationFinder = mock(PersonPhoneRelationFinder.class);
		contactList = new ContactList(writer, validator, personPhoneRelationFinder);

		when(validator.isValid(anyString())).thenReturn(true);
	}

	@Test
	public void testCallStoreWithPhoneNumberIdWhenInputIsNineNonStopNumbers() throws IOException {
		contactList.assignPhoneToPerson(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PERSON_ID1);
		verify(writer).storeRelationOf(TestUtils.TEST_PERSON_ID1, TestUtils.TEST_PHONE_NUMBER1);
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
