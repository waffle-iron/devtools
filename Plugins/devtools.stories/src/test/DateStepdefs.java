package test;

import cucumber.DateFormat;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import java.util.Date;

import com.github.funthomas424242.workgroup.Activator;

import static org.junit.Assert.assertEquals;

public class DateStepdefs {
	private String result;
	private Activator plugin;

	@Given("^today is (.+)$")
	public void today_is(@DateFormat("yyyy-MM-dd") Date date) {
		final String text = "";
	}

	@When("^I ask if (.+) is in the past$")
	public void I_ask_if_date_is_in_the_past(Date date) {
		final String text = "";
	}

	@Then("^the result should be (.+)$")
	public void the_result_should_be(String expectedResult) {
		assertEquals(expectedResult, result);
	}
}
