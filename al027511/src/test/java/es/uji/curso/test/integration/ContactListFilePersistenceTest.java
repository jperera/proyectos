package es.uji.curso.test.integration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.*;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;

public class ContactListFilePersistenceTest {

	private static final String FILE_FULL_PATH_SAMPLE = "src\\test\\resources\\file.data";
	private static final String LINE_SAMPLE1 = "this is a test";
	private static final String LINE_SAMPLE2 = "another line";
	private ContactListFilePersistence persistence;

	@Before
	public void setUp() {
		persistence = new ContactListFilePersistence(FILE_FULL_PATH_SAMPLE);
	}

	@Test
	public void testReadElementWrited() throws IOException {
		persistence.writeLine(LINE_SAMPLE1);

		Assert.assertEquals(LINE_SAMPLE1, persistence.readLines().get(0));
	}

	@Test
	public void testDifferentPersistenceWithSameName() throws IOException {
		ContactListFilePersistence persistence2 = new ContactListFilePersistence(FILE_FULL_PATH_SAMPLE);

		persistence.writeLine(LINE_SAMPLE1);

		String lineRead = persistence.readLines().get(0);
		Assert.assertEquals(lineRead, persistence2.readLines().get(0));
		Assert.assertEquals(LINE_SAMPLE1, lineRead);
	}

	@Test
	public void testReadTwoLinesWhenReadTwoLines() throws IOException {
		persistence.writeLine(LINE_SAMPLE1);
		persistence.writeLine(LINE_SAMPLE2);

		List<String> lines = persistence.readLines();

		Assert.assertEquals(LINE_SAMPLE1, lines.get(0));
		Assert.assertEquals(LINE_SAMPLE2, lines.get(1));
	}

	@After
	public void tearDown() {
		File file = new File(FILE_FULL_PATH_SAMPLE);
		file.delete();
	}

}
