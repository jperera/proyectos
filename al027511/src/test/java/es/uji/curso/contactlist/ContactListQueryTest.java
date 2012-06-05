package es.uji.curso.contactlist;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ContactListQueryTest {

	private static final String LINE_ELEMENT_SEPARATOR = " ";
	private static final String TEST_PHONE_NUMBER1 = "964000001";
	private static final String TEST_PHONE_NUMBER2 = "964000002";
	private static final int TEST_ID1 = 1;
	private static final int TEST_ID2 = 2;
	private static final String TEST_PERSON1_PHONE1_RELATION_LINE = TEST_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER1;
	private static final String TEST_PERSON1_PHONE2_RELATION_LINE = TEST_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER2;

	private ContactListQueryImpl query;
	private BufferedReader reader;
	private ContactListFactory factory;

	@Before
	public void setUp() {
		reader = mock(BufferedReader.class);
		factory = mock(ContactListFactory.class);
		query = new ContactListQueryImpl(factory, reader);
	}

	@Test
	public void testGetOnePhoneByIdWhenOnlyOnePhoneWithSameIdInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TEST_PHONE_NUMBER1);

		initQueryWithMockReaderAndMockFactory();
		Assert.assertEquals(phoneList, query.getPhonesById(TEST_ID1));
	}

	@Test
	public void testGetNoPhoneByIdWhenOnlyOnePhoneWithDiffIdInReader() throws IOException {
		initQueryWithMockReaderAndMockFactory();
		Assert.assertEquals(0, query.getPhonesById(TEST_ID2).size());
	}

	@Test
	public void testGetManyPhonesByIdWhenManyPhoneForPersonInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TEST_PHONE_NUMBER1, TEST_PHONE_NUMBER2);

		when(reader.readLine()).thenReturn(TEST_PERSON1_PHONE1_RELATION_LINE)
				.thenReturn(TEST_PERSON1_PHONE2_RELATION_LINE).thenReturn(null);

		initFactoryMockWithPersonPhoneRelationId1Phone1();
		initFactoryMockWithPersonPhoneRelationId1Phone2();

		Assert.assertEquals(phoneList, query.getPhonesById(TEST_ID1));
	}

	private List<String> getTestPhoneListWith(String... phones) {
		List<String> phonesResult = new ArrayList<String>();
		for (String phone : phones) {
			phonesResult.add(phone);
		}
		return phonesResult;
	}

	private void initQueryWithMockReaderAndMockFactory() throws IOException {
		initFactoryMockWithPersonPhoneRelationId1Phone1();
		when(reader.readLine()).thenReturn(TEST_PERSON1_PHONE1_RELATION_LINE).thenReturn(null);
	}

	private void initFactoryMockWithPersonPhoneRelationId1Phone1() throws IOException {
		PersonPhoneRelation personPhoneRelation = getMockPersonPhoneRelationId1Phone1();
		when(factory.createPersonPhoneRelationFor(TEST_PERSON1_PHONE1_RELATION_LINE)).thenReturn(personPhoneRelation);
	}

	private PersonPhoneRelation getMockPersonPhoneRelationId1Phone1() {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);

		when(personPhoneRelation.getPhone()).thenReturn(TEST_PHONE_NUMBER1);
		when(personPhoneRelation.isPerson(TEST_ID1)).thenReturn(true);
		return personPhoneRelation;
	}

	private void initFactoryMockWithPersonPhoneRelationId1Phone2() throws IOException {
		PersonPhoneRelation personPhoneRelation = getMockPersonPhoneRelationId1Phone2();
		when(factory.createPersonPhoneRelationFor(TEST_PERSON1_PHONE2_RELATION_LINE)).thenReturn(personPhoneRelation);
	}

	private PersonPhoneRelation getMockPersonPhoneRelationId1Phone2() {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);

		when(personPhoneRelation.getPhone()).thenReturn(TEST_PHONE_NUMBER2);
		when(personPhoneRelation.isPerson(TEST_ID1)).thenReturn(true);
		return personPhoneRelation;
	}
}
