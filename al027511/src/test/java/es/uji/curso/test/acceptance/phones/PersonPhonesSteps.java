package es.uji.curso.test.acceptance.phones;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.jbehave.core.annotations.*;

import es.uji.curso.contactlist.ContactListAPI;

public class PersonPhonesSteps {

	int person;
	String phone;

	@Given("a person and phone")
	public void initPersonAndPhone() {
		person = 1;
		phone = "964123456";
	}

	@When("I add the phone to the person")
	public void addAddressToPerson() throws IOException {
		ContactListAPI.assignPhoneToPerson(phone, person);
	}

	@Then("the person has the phone")
	public void testPersonHasTheAddress() throws IOException {
		List<String> phoneList = ContactListAPI.getPhonesById(person);
		Assert.assertEquals(phone, phoneList.get(0));
		tearDown();
	}

	private void tearDown() {
		File file = new File("contact_list_test.data");
		file.delete();
	}
}
