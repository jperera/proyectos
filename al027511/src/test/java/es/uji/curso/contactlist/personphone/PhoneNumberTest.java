package es.uji.curso.contactlist.personphone;

import java.io.IOException;

import static junit.framework.Assert.*;

import org.junit.Test;

import es.uji.curso.test.TestUtils;

public class PhoneNumberTest {

	@Test
	public void testPhoneIdForInternationalPhoneNumber() throws IOException {
		PhoneNumber phoneNumber = new PhoneNumber(TestUtils.TEST_PHONE_INTERNATIONAL_PHONE_NUMBER);
		assertEquals(TestUtils.TEST_PHONE_INTERNATIONAL_PHONE_NUMBER_WITHOUT_PLUS, phoneNumber.getId());
	}

	@Test
	public void testPhoneIdForPhoneNumberSeparatedBySpaces() throws IOException {
		PhoneNumber phoneNumber = new PhoneNumber(TestUtils.TEST_PHONE_NUMBER1_SEPARATED_BY_SPACES);
		assertEquals(TestUtils.TEST_PHONE_NUMBER1, phoneNumber.getId());
	}

	@Test
	public void testPhoneIdForInternationalPhoneNumberSeparatedBySpaces() {
		PhoneNumber phoneNumber = new PhoneNumber(TestUtils.TEST_PHONE_INTERNATIONAL_PHONE_NUMBER_WITH_SPACES);
		assertEquals(TestUtils.TEST_PHONE_INTERNATIONAL_PHONE_NUMBER_WITHOUT_PLUS, phoneNumber.getId());
	}
}
