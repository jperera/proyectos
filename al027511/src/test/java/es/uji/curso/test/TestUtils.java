package es.uji.curso.test;

public class TestUtils {

	public static final String FILE_FULL_PATH_SAMPLE = "src/test/resources/file.data";

	private static final String LINE_ELEMENT_SEPARATOR = " ";
	public static final int TEST_PERSON_ID1 = 1;
	public static final int TEST_ID2 = 2;
	public static final String TEST_PHONE_NUMBER1 = "964000000";
	public static final String TEST_PHONE_NUMBER2 = "964000002";
	public static final String TEST_PHONE_INTERNATIONAL_PHONE_NUMBER = "+34987654321";
	public static final String TEST_PHONE_INTERNATIONAL_PHONE_NUMBER_WITH_SPACES = "+34 987 65 43 21";
	public static final String TEST_PHONE_INTERNATIONAL_PHONE_NUMBER_WITHOUT_PLUS = "34987654321";
	public static final String TEST_PHONE_NUMBER1_SEPARATED_BY_SPACES = "964 00 00 00";
	public static final String TEST_PHONE_NUMBER2_WITH_3_2_2_2_GROUPS = "964 00 00 02";
	public static final String TEST_PERSON1_PHONE1_RELATION_LINE = TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER1;
	public static final String TEST_PERSON1_PHONE2_RELATION_LINE = TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER2;
	public static final String TEST_PERSON2_PHONE2_RELATION_LINE = TEST_ID2 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER2;

}
