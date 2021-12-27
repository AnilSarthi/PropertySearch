package com.property;

import com.property.model.User;
import com.property.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserJourneysStepDef {

    @Autowired
    private HomePage homePage;

    @Autowired
    private MyAccountPage myAccountPage;

    @Autowired
    private SearchResultsPage searchResultsPage;

    @Autowired
    private RegisterPage registerPage;

    @Autowired
    private CreateAlertAndSearchesPage createAlertAndSearchesPage;

    @Autowired
    private AlertAndSearchesPage alertAndSearchesPage;

    @Autowired
    private SignInPage signInPage;
    @Autowired
    private PropertyDetailsPage propertyDetailsPage;

    @Value("${test.url}")
    private String testUrl;

    @When("^I perform vehicle enquiry$")
    public void iGetVehicleInformation() throws Throwable {
        homePage.navigateToPage(testUrl);
    }

    @Given("I am on zoopla homepage")
    public void iAmOnZooplaHomepage() throws InterruptedException {
        homePage.navigateToPage(testUrl);
        homePage.acceptAllCookies();
    }

    @When("I select my search type {string}")
    public void iSelectMySearchType(String searchType) {
        homePage.selectSearchType(searchType);
    }

    @And("I select to create email alerts")
    public void iSelectToCreateEmailAlerts() {
        searchResultsPage.clickOnCreateAlert();
    }

    @And("I register")
    public void iRegister() {
        registerPage.registerAnUser(User.buildUser());
    }

    @Then("I set {string} the email alerts")
    public void iSetTheEmailAlerts(String alertType) {
        createAlertAndSearchesPage.setAlertAndSave(alertType);
    }


    @When("I sign in with valid details")
    public void iSignInWithValidDetails() {
        homePage.clickOnSignIn();
        signInPage.login(User.buildMyUser());

    }

    @And("navigate to alerts and searches page")
    public void navigateToAlertsAndSearchesPage() {
        myAccountPage.clickOnAlertsAndSearches();
        homePage.clickOnSaveSearch();
    }

    @Then("I change the existing alerts frequency {string}")
    public void iChangeTheExistingAlertsFrequencyFrequency(String alertType) {
        myAccountPage.clickOnAlertsAndSearches();
        alertAndSearchesPage.selectFrequencyFromDropdown(alertType);
    }

    @Then("I see the alert frequency has changed")
    public void iSeeTheAlertFrequencyHasChanged() {
    }

    @Then("I see the first result matches (.*) in the search")
    public void iSeeTheFirstResultMatchesTheSearch(String location) {
        assertThat(searchResultsPage.getAreaOfFisrtSearchResult(), containsString(location.replace(",","")));
    }
    @And("I enter search criteria with a keyword")
    public void iEnterSearchCriteriaWithAKeyword() {
    }


    @Then("I see the search {string} keyword appears in the description of the property")
    public void iSeeTheSearchKeywordAppearsInTheDescriptionOfTheProperty(String keyword) {
        SoftAssertions softAssertions = new SoftAssertions();
        Map<String, Boolean> propertiesHasSearchTermResultsMap = propertyDetailsPage.getPropertiesHasSearchTerm(keyword);
        propertiesHasSearchTermResultsMap.keySet().stream().forEach(key ->
                softAssertions.assertThat(propertiesHasSearchTermResultsMap.get(key))
                        .overridingErrorMessage(String.format("Url %s not contained search term %s", key, keyword)).isEqualTo(true));
        softAssertions.assertAll();
    }

    @When("select my search type <sale>")
    public void selectMySearchTypeSale() {
    }

    @And("I save my search")
    public void iSaveMySearch() {
        homePage.clickOnSaveSearch();
    }

    @Then("I see the search is saved")
    public void iSeeTheSearchIsSaved() {
        Assert.assertTrue("The save is not successful", homePage.isSaveSearchConfirmDisplayed());
    }

    @When("I select my search type <rent>")
    public void iSelectMySearchTypeRent() {
    }

    @When("I enter search criteria")
    public void iEnterSearchCriteria() {
    }

    @Then("I see email alerts are saved")
    public void iSeeEmailAlertsAreSaved() {
        Assert.assertTrue("Alert Sucess message not displayed ", alertAndSearchesPage.isAlertSuccessMessageDisplayed());
    }

    @When("I search for Rent a property with below criteria")
    public void iSearchForRentAPropertWithBelowCriteria(DataTable dataTable) {
        List<Map<String, String>> mapsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = mapsList.get(0);
        homePage.setSearchTerm(map.get("SearchLocation"));
        homePage.setMinNoOfBeds(map.get("Bedrooms"));
        homePage.setRentMaxPrice(map.get("MaxPrice"));
        homePage.setRentMinPrice(map.get("MinPrice"));
        homePage.search();

    }

    @When("I search a property for sale with below criteria")
    public void iSearchAPropertyForSaleWithBelowCriteria(DataTable dataTable) {
        List<Map<String, String>> mapsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = mapsList.get(0);
        homePage.setSearchTerm(map.get("SearchLocation"));
        homePage.setMinNoOfBeds(map.get("Bedrooms"));
        homePage.setMinPriceForSale(map.get("MinPrice"));
        homePage.setMaxPriceForSale(map.get("MaxPrice"));
        homePage.setPropertyType(map.get("PropertyType"));
        homePage.clickOnAdvanceSearchOptions();
        homePage.setDistanceFromLocation(map.get("distance"));
        homePage.setKeywords(map.get("Keywords"));
        homePage.search();
    }


    @Then("I see alerts frequency set to {string}")
    public void iSeeAlertsFrequencySetTo(String alertsFrequency) {
        Assert.assertEquals("Alerts frequency did not match", alertsFrequency, alertAndSearchesPage.getFrequencyAlert());
    }
}

