package es.uji.curso.test;

public class TestUtils {
	
	public static final String FILE_FULL_PATH_SAMPLE = "src/test/resources/file.data";

	private static final String LINE_ELEMENT_SEPARATOR = " ";
	public static final int TEST_PERSON_ID1 = 1;
	public static final int TEST_ID2 = 2;
	public static final String TEST_PHONE_NUMBER1 = "964000000";
	public static final String TEST_PHONE_NUMBER2 = "964000002";

	public static final String TEST_PERSON1_PHONE1_RELATION_LINE = TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER1;
	public static final String TEST_PERSON1_PHONE2_RELATION_LINE = TEST_PERSON_ID1 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER2;
	public static final String TEST_PERSON2_PHONE2_RELATION_LINE = TEST_ID2 + LINE_ELEMENT_SEPARATOR
			+ TEST_PHONE_NUMBER2;

}
