package testcases;

import org.testng.annotations.Test;

import components.BaseClass;
import dataModel.User;
import pageObjects.HomePage;
import pageObjects.LocationManagementPage;

public class Location_Management extends BaseClass {

	User user = getUserTestData();
	HomePage homePage = new HomePage();
	LocationManagementPage locationManagementPage = new LocationManagementPage();

	@Test
	public void TC_01_CreateLocation_as_PP_Operator_and_DefaultRates() {
		homePage = launchApplication();
		locationManagementPage = homePage.navigateToLocationManagement();
		String locName = locationManagementPage.createLocation("Premium Parking", "Default");
		locationManagementPage.verifyPNumberOnGrid_AfterLocationCreation(locName);

	}

	@Test
	public void TC_02_CreateLocation_as_PP_Operator_and_EVRates() {
		homePage = launchApplication();
		locationManagementPage = homePage.navigateToLocationManagement();
		String locName = locationManagementPage.createLocation("Premium Parking", "EV Rates");
		locationManagementPage.verifyPNumberOnGrid_AfterLocationCreation(locName);

	}
	
	@Test
	public void TC_03_CreateLocation_as_Other_Operator_and_DefaultRates() {
		homePage = launchApplication();
		locationManagementPage = homePage.navigateToLocationManagement();
		String locName = locationManagementPage.createLocation("ABC Parking", "Default");
		locationManagementPage.verifyPNumberOnGrid_AfterLocationCreation(locName);

	}

	@Test
	public void TC_04_CreateLocation_as_Other_Operator_and_EVRates() {
		homePage = launchApplication();
		locationManagementPage = homePage.navigateToLocationManagement();
		String locName = locationManagementPage.createLocation("SP+", "EV Rates");
		locationManagementPage.verifyPNumberOnGrid_AfterLocationCreation(locName);

	}

}
