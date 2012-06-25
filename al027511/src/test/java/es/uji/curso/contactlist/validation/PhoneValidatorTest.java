package es.uji.curso.contactlist.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PhoneValidatorTest {

	private static final String PHONE_NUMBER_WITH_ONE_SPACE_PER_DIGIT = "1 2 3 4 5 6 7 8 9";
	private static final String PHONE_NUMBER_WITH_3_2_2_2_GROUPS = "123 45 67 89";
	private static final String PHONE_NUMBER_WITH_TEN_CHARS = "1234567890";
	private static final String EMPTY_STRING = "";
	private static final String PHONE_NUMBER_NON_STOP_NINE_NUMBERS = "123456789";
	private static final String PHONE_NUMBER_WITH_ALFA_CHAR = "123A56789";
	
	private ContactListValidator validator;

	@Before
	public void setUp() {
		validator = new PhoneValidator();
	}

	@Test
	public void testReturnValidNumberWhenNonStopNineNumbers() {
		assertTrue(validator.isValid(PHONE_NUMBER_NON_STOP_NINE_NUMBERS));
	}

	@Test
	public void testReturnInvalidNumberWhenHasAlfaChar() {
		assertFalse(validator.isValid(PHONE_NUMBER_WITH_ALFA_CHAR));
	}
	
	@Test
	public void testReturnInvalidNumberWhenEmptyPhone() {
		assertFalse(validator.isValid(EMPTY_STRING));
	}
	
	@Test
	public void testReturnInvalidWhenLenghtIsMoreThanNineInLocalNumbers() {
		assertFalse(validator.isValid(PHONE_NUMBER_WITH_TEN_CHARS));
	}
	
	@Test
	public void testReturnValidWhenIsSeparateBySpaces3_2_2_2() {
		assertTrue(validator.isValid(PHONE_NUMBER_WITH_3_2_2_2_GROUPS));
	}
	
	@Test
	public void testReturnInvalidWithOneSpaceByDigit() {
		assertFalse(validator.isValid(PHONE_NUMBER_WITH_ONE_SPACE_PER_DIGIT));
	}
	
	@Test
	public void testReturnInvalidWhenThereAreGroupsWithTwoDigitsAndTheLastHasThree() {
		assertFalse(validator.isValid("12 34 56 789"));
	}
	
}
