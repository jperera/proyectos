package es.uji.curso.contactlist.validation;

public class PhoneValidator implements ContactListValidator {

	private static final String INTERNATIONAL_PREFIX = "(\\+[0-9][0-9] ?)";

	public boolean isValid(String phoneNumber) {
		return isValidNumberWithGroupsOfTwoDigits(phoneNumber) || isValidNumberWithoutGroupsOfTwoDigits(phoneNumber);
	}

	private boolean isValidNumberWithoutGroupsOfTwoDigits(String phoneNumber) {
		return phoneNumber.matches("^" + INTERNATIONAL_PREFIX + "?(?:[0-9]{3} ?){3}$");
	}

	private boolean isValidNumberWithGroupsOfTwoDigits(String phoneNumber) {
		return phoneNumber.matches("^" + INTERNATIONAL_PREFIX + "?(?:[0-9][0-9][0-9]? ?){2}(?:[0-9][0-9] ?){2}$");
	}

}
