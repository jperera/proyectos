package es.uji.curso.contactlist.test.integration;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import es.uji.curso.contactlist.persistence.ContactListFilePersistence;

public class ContactListFilePersistenceTest {

	private static final String FILE_FULL_PATH_SAMPLE = "test\\file.data";
	private static final String LINE_SAMPLE = "this is a test";

	@Test
	public void testReadElementWrited() throws IOException {
		ContactListFilePersistence persistence = new ContactListFilePersistence(FILE_FULL_PATH_SAMPLE);
	
		persistence.write(LINE_SAMPLE);
		
		Assert.assertEquals(LINE_SAMPLE, persistence.readline());
	}
	
	@Test
	public void testDifferentPersistenceWithSameName() throws IOException {
		
		ContactListFilePersistence persistence1 = new ContactListFilePersistence(FILE_FULL_PATH_SAMPLE);
		ContactListFilePersistence persistence2 = new ContactListFilePersistence(FILE_FULL_PATH_SAMPLE);
		
		persistence1.write(LINE_SAMPLE);
		
		Assert.assertEquals(persistence1.readline(), persistence2.readline());
		Assert.assertEquals(LINE_SAMPLE, persistence1.readline());
	}
	
	@After
	public void tearDown() {
		File file = new File(FILE_FULL_PATH_SAMPLE);
		file.delete();
	}

}
