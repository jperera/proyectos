package es.uji.curso.contactlist.personphone;

public class PhoneNumber {

	private static final String INTERNATIONAL_SYMBOL = "+";
	private static final String EMPTY_STRING = "";
	private static final String WHITE_SPACE = " ";
	String id;

	public PhoneNumber(String phoneNumber) {
		id = getIdFrom(phoneNumber);
	}

	public String getId() {
		return id;
	}

	private String getIdFrom(String phoneNumber) {
		String result = phoneNumber;
		if (isInternationalPhoneNumber(result)) {
			result = cleaInternationalNonDigitInfo(phoneNumber);
		}
		return result.replace(WHITE_SPACE, EMPTY_STRING);
	}

	private String cleaInternationalNonDigitInfo(String phoneNumber) {
		return phoneNumber.substring(1);
	}

	private boolean isInternationalPhoneNumber(String phoneNumber) {
		return phoneNumber.startsWith(INTERNATIONAL_SYMBOL);
	}
}
