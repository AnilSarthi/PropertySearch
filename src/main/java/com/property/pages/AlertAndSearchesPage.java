package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertAndSearchesPage {
    private final By ALERT_SUCCESS_SELECTOR = By.id("alert-success");
    private final By FREQUENCY_SELECTOR = By.cssSelector(".myaccount-alert-col .js-myaccount-alert-frequency.js-check.js-touched");
    private final By FREQUENCY_SELECTED_SELECTOR = By.cssSelector(".myaccount-alert-col .js-myaccount-alert-frequency.js-check.js-touched option[selected]");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public boolean isAlertSuccessMessageDisplayed() {
        webDriverHelper.waitForElementToPresent(ALERT_SUCCESS_SELECTOR);
        webDriverHelper.clickOnElement(ALERT_SUCCESS_SELECTOR);
        return webDriverHelper.isElementDisplayed(ALERT_SUCCESS_SELECTOR);
    }
    public void selectFrequencyFromDropdown(String frequency){
        webDriverHelper.waitForElementToPresent(FREQUENCY_SELECTOR);
        webDriverHelper.selectValueFromDropdown(FREQUENCY_SELECTOR,frequency);
    }
    public String getFrequencyAlert(){
        webDriverHelper.refreshCurrentPage();
        return webDriverHelper.getText(FREQUENCY_SELECTED_SELECTOR).trim();
    }
}
