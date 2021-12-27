package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsPage {
    private final By CREATE_EMAIL_ALERT_SELECTOR = By.cssSelector("[data-testid='create-email-alert-button']");
    private final By FIRST_RESULT_AREA_SELECTOR = By.cssSelector("main[data-testid='search-content']  div[data-testid*=search-result_listing]:nth-child(1)  a:nth-child(3) p");
    private final By SEARCH_RESULTS_AREA_SELECTOR = By.cssSelector("main[data-testid='search-content']  div[data-testid*=search-result_listing]:nth-child(1)  a:nth-child(3) p");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void clickOnCreateAlert() {
        webDriverHelper.waitForElementToPresent(CREATE_EMAIL_ALERT_SELECTOR,10);
        webDriverHelper.clickOnElement(CREATE_EMAIL_ALERT_SELECTOR);
    }

    public String getAreaOfFisrtSearchResult() {
    webDriverHelper.waitForElementToPresent(FIRST_RESULT_AREA_SELECTOR);
    return webDriverHelper.getText(FIRST_RESULT_AREA_SELECTOR);
    }

}
