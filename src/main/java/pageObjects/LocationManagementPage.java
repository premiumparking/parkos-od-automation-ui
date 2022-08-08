package pageObjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import components.BaseClass;

public class LocationManagementPage extends BaseClass {

	String pNumber = "P" + get4DigitRandomNumber();
	String cNumber = "C" + get4DigitRandomNumber();

	public WebElement button_CreateLocation() {
		return driver.findElement(By.id("create-location"));
	}

	public WebElement textbox_Address_on_CreateLocation() {
		return driver.findElement(By.id("address-field"));
	}

	public WebElement text_Location_Creation() {
		return driver.findElement(By.id("responsive-dialog-title"));
	}

	public WebElement button_Next() {
		return driver.findElement(By.id("next-button"));
	}

	public WebElement dropdown_Operator() {
		return driver.findElement(By.id("operator"));
	}

	public WebElement label_Select_Operator() {
		return driver.findElement(By.id("label-select-operator"));
	}

	public WebElement textbox_PNumber() {
		return driver.findElement(By.id("p-number"));
	}

	public WebElement label_Rate_Structure() {
		return driver.findElement(By.id("label-rate-structure"));
	}

	public WebElement dropdown_RateStructure() {
		return driver.findElement(By.id("rate-structure"));
	}

	public WebElement dd_OperatorValue(String text) {
		return driver.findElement(By.xpath(".//li[@data-value='" + text + "']"));
	}

	public WebElement dd_RateName(String text) {
		return driver.findElement(By.xpath(".//li[@data-value='" + text + "']"));
	}

	public WebElement pNumberOnGrid(String text) {
		return driver.findElement(By.id(text));
	}

	public void fillAddress() {
		String address = "TestAddress_" + getTimestamp();
		enterText(textbox_Address_on_CreateLocation(), address, "Address");
		clickOnButton(button_Next(), "Next");
		waitForPageLoad(1);
	}

	public String EnterPNumber(String operator) {
		String locNumber = null;

		assertEquals(label_Select_Operator().getText(), "Select Operator");
		passStep("Select Operator drop down label name is :" + label_Select_Operator().getText());
		// selectDropdown(dropdown_Operator(), "Premium Parking", " Select Operator");
		clickOnButton(dropdown_Operator(), "Select Operator");
		clickOnButton(dd_OperatorValue(operator), operator);
		if (operator.equalsIgnoreCase("Premium Parking")) {
			enterText(textbox_PNumber(), pNumber, "P Number");
			locNumber = pNumber;
		} else {
			enterText(textbox_PNumber(), cNumber, "C Number");
			locNumber = cNumber;
		}
		clickOnButton(button_Next(), "Next");
		waitForPageLoad(1);
		return locNumber;
	}

	public void select_Default_RateStructure(String rateStructure) {
		assertEquals(label_Rate_Structure().getText(), "Select Rate Structure");
		passStep("Select Rate Structure drop down label name is :" + label_Rate_Structure().getText());
		// selectDropdown(dropdown_RateStructure(), "Default", "Rate Structure");
		clickOnButton(dropdown_RateStructure(), "Rate Structure");
		clickOnButton(dd_RateName(rateStructure), rateStructure);
		waitForPageLoad(1);
	}

	public String createLocation(String operator, String rateStructure) {

		clickOnButton(button_CreateLocation(), " + Create Location");
		waitForPageLoad(1);
		assertEquals(text_Location_Creation().getText(), "Location Creation");
		passStep("Name on location creation wizard is : " + text_Location_Creation().getText());

		fillAddress();
		String locNumber = EnterPNumber(operator);
		select_Default_RateStructure(rateStructure);
		clickOnButton(button_Next(), "Save");
		waitForPageLoad(3);
		return locNumber;
	}

	public void verifyPNumberOnGrid_AfterLocationCreation(String locName) {
		waitForElementTobeDisplayed(By.id(locName));
		if (isElementDisplayed(pNumberOnGrid(locName)))
			passStep("newly created location " + locName + " is displayed on grid");

	}

}
