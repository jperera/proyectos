package es.uji.curso.contactlist.personphone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.contactlist.ContactListFactory;
import es.uji.curso.contactlist.persistence.LinesReader;
import es.uji.curso.contactlist.personphone.PersonPhoneRelation;
import es.uji.curso.contactlist.personphone.PersonPhoneRelationFinderImpl;
import es.uji.curso.test.TestUtils;

public class PersonPhoneRelationFinderTest {

	private static final String LINE_ELEMENT_SEPARATOR = " ";
	private static final int TEST_ID2 = 2;
	private static final String TEST_PERSON1_PHONE1_RELATION_LINE = TestUtils.TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TestUtils.TEST_PHONE_NUMBER1;
	private static final String TEST_PERSON1_PHONE2_RELATION_LINE = TestUtils.TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TestUtils.TEST_PHONE_NUMBER2;
	private static final String TEST_PERSON2_PHONE2_RELATION_LINE = TEST_ID2 + LINE_ELEMENT_SEPARATOR
			+ TestUtils.TEST_PHONE_NUMBER2;

	private PersonPhoneRelationFinderImpl query;
	private LinesReader reader;
	private ContactListFactory factory;

	@Before
	public void setUp() {
		reader = mock(LinesReader.class);
		factory = mock(ContactListFactory.class);
		query = new PersonPhoneRelationFinderImpl(factory, reader);
	}

	@Test
	public void testGetOnePhoneByIdWhenOnlyOnePhoneWithSameIdInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TestUtils.TEST_PHONE_NUMBER1);

		initQueryWithMockReaderAndMockFactory();
		Assert.assertEquals(phoneList, query.findPhonesByPersonId(TestUtils.TEST_PERSON_ID1));
	}

	@Test
	public void testGetNoPhoneByIdWhenOnlyOnePhoneWithDiffIdInReader() throws IOException {
		initQueryWithMockReaderAndMockFactory();
		Assert.assertEquals(0, query.findPhonesByPersonId(TEST_ID2).size());
	}

	@Test
	public void testGetManyPhonesByIdWhenManyPhoneForPersonInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PHONE_NUMBER2);
		List<String> expectedRelationsList = getRelationListWith(TEST_PERSON1_PHONE1_RELATION_LINE,
				TEST_PERSON1_PHONE2_RELATION_LINE);

		when(reader.readLines()).thenReturn(expectedRelationsList);

		initFactoryMockWithPersonPhoneRelationId1Phone1();
		initFactoryMockWithPersonPhoneRelationId1Phone2();

		Assert.assertEquals(phoneList, query.findPhonesByPersonId(TestUtils.TEST_PERSON_ID1));
	}

	private List<String> getRelationListWith(String... personPhoneRelations) {
		List<String> expectedRelationsList = new ArrayList<String>();

		for (String personPhoneRelation : personPhoneRelations) {
			expectedRelationsList.add(personPhoneRelation);
		}
		return expectedRelationsList;
	}

	@Test
	public void testGetOnePhoneByIdWhenManyPersonsInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TestUtils.TEST_PHONE_NUMBER1);

		List<String> personPhoneRelations = new ArrayList<String>();
		personPhoneRelations.add(TEST_PERSON1_PHONE1_RELATION_LINE);
		personPhoneRelations.add(TEST_PERSON2_PHONE2_RELATION_LINE);
		when(reader.readLines()).thenReturn(personPhoneRelations);

		initFactoryMockWithPersonPhoneRelationId1Phone1();
		initFactoryMockWithPersonPhoneRelationId2Phone2();

		Assert.assertEquals(phoneList, query.findPhonesByPersonId(TestUtils.TEST_PERSON_ID1));
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
		List<String> expectedPhoneList = new ArrayList<String>();

		expectedPhoneList.add(TEST_PERSON1_PHONE1_RELATION_LINE);
		when(reader.readLines()).thenReturn(expectedPhoneList);
	}

	private void initFactoryMockWithPersonPhoneRelationId1Phone1() throws IOException {
		PersonPhoneRelation personPhoneRelation = getMockPersonPhoneRelationId1Phone1();
		when(factory.createPersonPhoneRelationFrom(TEST_PERSON1_PHONE1_RELATION_LINE)).thenReturn(personPhoneRelation);
	}

	private void initFactoryMockWithPersonPhoneRelationId1Phone2() throws IOException {
		PersonPhoneRelation personPhoneRelation = getMockPersonPhoneRelationId1Phone2();
		when(factory.createPersonPhoneRelationFrom(TEST_PERSON1_PHONE2_RELATION_LINE)).thenReturn(personPhoneRelation);
	}

	private void initFactoryMockWithPersonPhoneRelationId2Phone2() throws IOException {
		PersonPhoneRelation personPhoneRelation = getMockPersonPhoneRelationId2Phone2();
		when(factory.createPersonPhoneRelationFrom(TEST_PERSON2_PHONE2_RELATION_LINE)).thenReturn(personPhoneRelation);
	}

	private PersonPhoneRelation getMockPersonPhoneRelationId1Phone1() {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);

		when(personPhoneRelation.getPhone()).thenReturn(TestUtils.TEST_PHONE_NUMBER1);
		when(personPhoneRelation.isPerson(TestUtils.TEST_PERSON_ID1)).thenReturn(true);
		return personPhoneRelation;
	}

	private PersonPhoneRelation getMockPersonPhoneRelationId1Phone2() {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);

		when(personPhoneRelation.getPhone()).thenReturn(TestUtils.TEST_PHONE_NUMBER2);
		when(personPhoneRelation.isPerson(TestUtils.TEST_PERSON_ID1)).thenReturn(true);
		return personPhoneRelation;
	}

	private PersonPhoneRelation getMockPersonPhoneRelationId2Phone2() {
		PersonPhoneRelation personPhoneRelation = mock(PersonPhoneRelation.class);

		when(personPhoneRelation.getPhone()).thenReturn(TestUtils.TEST_PHONE_NUMBER2);
		when(personPhoneRelation.isPerson(TEST_ID2)).thenReturn(true);
		return personPhoneRelation;
	}
}
