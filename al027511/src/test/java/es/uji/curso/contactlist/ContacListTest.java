package es.uji.curso.contactlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ContacListTest {

	private static final int TEST_PERSON_ID1 = 1;
	private static final String TEST_PHONE_NUMBER1 = "964000000";
	private static final String TEST_PHONE_NUMBRE2 = "612345678";
	
	private ContactListQuery query;
	private ContactListFactory factory;
	private ContactList contactList;

	@Before
	public void setUp() {
		query = mock(ContactListQuery.class);
		factory = mock(ContactListFactory.class);
		contactList = new ContactList(factory, query);
	}
	
	@Test
	public void testAssignPhoneToPersonColaboration() throws IOException {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);
		when(factory.createPersonPhoneRelationFor(TEST_PHONE_NUMBER1, TEST_PERSON_ID1)).thenReturn(personPhoneRelation);
		
		contactList.assignPhoneToPerson(TEST_PHONE_NUMBER1, TEST_PERSON_ID1);
		
		verify(factory).createPersonPhoneRelationFor(TEST_PHONE_NUMBER1, TEST_PERSON_ID1);
	}

	@Test
	public void testRetrieveOnePhoneForPersistence() throws IOException {
		List<String> phoneList = new ArrayList<String>();
		phoneList.add(TEST_PHONE_NUMBER1);
		when(query.getPhonesById(TEST_PERSON_ID1)).thenReturn(phoneList);
		assertEquals(phoneList, contactList.getPhoneById(TEST_PERSON_ID1));
	}
	
	@Test
	public void testRetrieveManyPhonesForOneId() throws IOException {
		List<String> phoneList = new ArrayList<String>();
		phoneList.add(TEST_PHONE_NUMBER1);
		phoneList.add(TEST_PHONE_NUMBRE2);
		when(query.getPhonesById(TEST_PERSON_ID1)).thenReturn(phoneList);
		assertEquals(phoneList, contactList.getPhoneById(TEST_PERSON_ID1));
	}
	
	@Test
	public void testCallPersistorWithIdWhenGetPhoneByThisId() throws IOException {
		contactList.getPhoneById(TEST_PERSON_ID1);
		verify(query).getPhonesById(TEST_PERSON_ID1);
	}

}
