package es.uji.curso.contactlist.personphone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import es.uji.curso.contactlist.persistence.LinesReader;
import es.uji.curso.test.TestUtils;

public class PersonPhoneRelationFinderTest {

	private PersonPhoneRelationFinderImpl query;
	private LinesReader reader;

	@Before
	public void setUp() {
		reader = mock(LinesReader.class);
		query = new PersonPhoneRelationFinderImpl(reader);
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
		Assert.assertEquals(0, query.findPhonesByPersonId(TestUtils.TEST_ID2).size());
	}

	@Test
	public void testGetManyPhonesByIdWhenManyPhoneForPersonInReader() throws IOException {
		List<String> phoneList = getTestPhoneListWith(TestUtils.TEST_PHONE_NUMBER1, TestUtils.TEST_PHONE_NUMBER2);
		List<String> expectedRelationsList = getRelationListWith(TestUtils.TEST_PERSON1_PHONE1_RELATION_LINE,
				TestUtils.TEST_PERSON1_PHONE2_RELATION_LINE);

		when(reader.readLines()).thenReturn(expectedRelationsList);

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
		personPhoneRelations.add(TestUtils.TEST_PERSON1_PHONE1_RELATION_LINE);
		personPhoneRelations.add(TestUtils.TEST_PERSON2_PHONE2_RELATION_LINE);
		when(reader.readLines()).thenReturn(personPhoneRelations);

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
		List<String> expectedPhoneList = new ArrayList<String>();

		expectedPhoneList.add(TestUtils.TEST_PERSON1_PHONE1_RELATION_LINE);
		when(reader.readLines()).thenReturn(expectedPhoneList);
	}
	
}
