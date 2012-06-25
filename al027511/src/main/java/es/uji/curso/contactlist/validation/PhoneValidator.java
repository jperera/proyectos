package es.uji.curso.contactlist.validation;

public class PhoneValidator implements ContactListValidator {

	private static final String SPACE = " ";
	private static final int MAX_LOCAL_PHONE_SIZE = 9;

	public boolean isValid(String phoneNumber) {
		String[] phoneGroups = getPhoneGroups(phoneNumber);
		return areValidPhoneGroups(phoneGroups);
	}

	private boolean areValidPhoneGroups(String[] phoneGroups) {
		boolean isValid = true;
		for (int i = 0; i < lastGroupPosition(phoneGroups); i++) {
			isValid = isValid && isValidPhoneGroup(phoneGroups[i]);
		}
		String lastPhoneGroup = phoneGroups[lastGroupPosition(phoneGroups)];
		isValid = isValid && isValidPhoneGroup(lastPhoneGroup)
				&& !isThreeDigitsPhoneGroup(lastPhoneGroup);

		return isValid;
	}

	private String[] getPhoneGroups(String phoneNumber) {
		return phoneNumber.split(SPACE);
	}

	private int lastGroupPosition(String[] phoneGroups) {
		return phoneGroups.length - 1;
	}

	private boolean isValidPhoneGroup(String phoneGroup) {
		return observeMinimalGroupSize(phoneGroup) && observeLocalPhoneMaxSize(phoneGroup) && isNumeric(phoneGroup);
	}

	private boolean isThreeDigitsPhoneGroup(String phoneGroup) {
		return phoneGroup.length() == 3;
	}

	private boolean observeMinimalGroupSize(String phoneGroup) {
		return phoneGroup.length() > 1;
	}

	private boolean observeLocalPhoneMaxSize(String candidateNumbers) {
		return candidateNumbers.length() <= MAX_LOCAL_PHONE_SIZE;
	}

	private boolean isNumeric(String candidateNumber) {
		try {
			Integer.parseInt(candidateNumber);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
