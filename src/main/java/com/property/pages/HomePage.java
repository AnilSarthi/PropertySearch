package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class HomePage {
    private final By FOR_SALE_SELECTOR = By.id("search-tabs-for-sale");
    private final By TO_RENT_SELECTOR_XPATH = By.xpath("//*[@data-track-label='to rent']");
    private final By TO_RENT_SELECTOR = By.id("search-tabs-to-rent");
    private final By SEARCH_INPUT_SELECTOR = By.id("search-input-location");
    private final By RENT_MIN_PRICE_SELECTOR = By.id("rent_price_min_per_month");
    private final By RENT_MAX_PRICE_SELECTOR = By.id("rent_price_max_per_month");
    private final By FOR_SALE_MIN_PRICE_SELECTOR = By.id("forsale_price_min");
    private final By FOR_SALE_MAX_PRICE_SELECTOR = By.id("forsale_price_max");
    private final By MIN_NO_OF_BEDROOMS_SELECTOR = By.id("beds_min");
    private final By ADVANCE_SEARCH_OPTIONS_SELECTOR = By.cssSelector(".search-advanced-toggle.link");
    private final By PROPERTY_TYPE_OPTIONS_SELECTOR = By.id("property_type");
    private final By KEYWORDS_SELECTOR = By.id("keywords");
    private final By DISTANCE_IN_LOCATION_SELECTOR = By.id("radius");
    private final By SEARCH_SELECTOR = By.id("search-submit");
    private final By SIGN_IN_SELECTOR = By.linkText("Sign in");
    //private final By ACCEPT_ALL_COOKIES_SELECTOR = By.cssSelector(".ui-button-primary.ui-cookie-accept-all-medium-large");
    private final By ACCEPT_ALL_COOKIES_SELECTOR = By.xpath("//button[.='Accept all cookies']");
    private final By SAVE_THIS_SEARCH_SELECTOR = By.linkText("Save this search");
    private final By SAVE_SEARCH_CONFIRMATION_SELECTOR = By.cssSelector("div[data-testid='modal'] div:nth-child(2) div>h4");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void selectSearchType(String searchType) {
        webDriverHelper.waitForElementToPresent(FOR_SALE_SELECTOR);

        switch (searchType.toUpperCase(Locale.ROOT)) {
            case "SALE":
                webDriverHelper.clickOnElement(FOR_SALE_SELECTOR);
                break;
            case "RENT":
                //webDriverHelper.clickOnElement(TO_RENT_SELECTOR);
                webDriverHelper.clickOnElement(TO_RENT_SELECTOR_XPATH);
                break;
            default:
                throw new RuntimeException("Invalid search type selected " + searchType);
        }
    }

    public void setSearchTerm(String searchTerm) {
        webDriverHelper.setTextInTextbox(SEARCH_INPUT_SELECTOR, searchTerm);
    }

    public void search() {
        webDriverHelper.waitForElementToPresent(SEARCH_SELECTOR);
        webDriverHelper.clickOnElement(SEARCH_SELECTOR);
    }

    public void navigateToPage(String url) {
        webDriverHelper.navigateToURL(url);
    }

    public void setRentMinPrice(String minPrice) {
        webDriverHelper.waitForElementToPresent(RENT_MIN_PRICE_SELECTOR);
        webDriverHelper.selectValueFromDropdown(RENT_MIN_PRICE_SELECTOR, minPrice);
    }

    public void setKeywords(String keywords) {
        webDriverHelper.setTextInTextbox(KEYWORDS_SELECTOR, keywords);
    }

    public void setRentMaxPrice(String maxPrice) {
        webDriverHelper.waitForElementToPresent(RENT_MAX_PRICE_SELECTOR);
        webDriverHelper.selectValueFromDropdown(RENT_MAX_PRICE_SELECTOR, maxPrice);

    }

    public void setMinNoOfBeds(String noOfBeds) {
        webDriverHelper.selectValueFromDropdown(MIN_NO_OF_BEDROOMS_SELECTOR, noOfBeds);
    }

    public void acceptAllCookies() throws InterruptedException {
        Thread.sleep(10000);
        webDriverHelper.waitForElementToPresent(ACCEPT_ALL_COOKIES_SELECTOR);
        webDriverHelper.clickOnElement(ACCEPT_ALL_COOKIES_SELECTOR);
    }

    public void clickOnSignIn(){
        webDriverHelper.waitForElementToPresent(SIGN_IN_SELECTOR);
        webDriverHelper.clickOnElement(SIGN_IN_SELECTOR);
    }

    public void setMaxPriceForSale(String forSaleMaxPrice){
        webDriverHelper.selectValueFromDropdown(FOR_SALE_MAX_PRICE_SELECTOR,forSaleMaxPrice);

    }

    public void setMinPriceForSale(String forSaleMinPrice){
        webDriverHelper.selectValueFromDropdown(FOR_SALE_MIN_PRICE_SELECTOR,forSaleMinPrice);
    }

    public void setDistanceFromLocation(String distance){
        webDriverHelper.selectValueFromDropdown(DISTANCE_IN_LOCATION_SELECTOR,distance);
    }

    public void setPropertyType(String propertyType){
        webDriverHelper.selectValueFromDropdown(PROPERTY_TYPE_OPTIONS_SELECTOR,propertyType);
    }

    public void clickOnAdvanceSearchOptions(){
        webDriverHelper.waitForElementToPresent(ADVANCE_SEARCH_OPTIONS_SELECTOR);
        webDriverHelper.clickOnElement(ADVANCE_SEARCH_OPTIONS_SELECTOR);
    }


    public void clickOnSaveSearch() {
        webDriverHelper.waitForElementToPresent(SAVE_THIS_SEARCH_SELECTOR);
        webDriverHelper.clickOnElement(SAVE_THIS_SEARCH_SELECTOR);
    }

    public boolean isSaveSearchConfirmDisplayed(){
        webDriverHelper.waitForElementToPresent(SAVE_SEARCH_CONFIRMATION_SELECTOR);
        return webDriverHelper.isElementDisplayed(SAVE_SEARCH_CONFIRMATION_SELECTOR);
    }
}
