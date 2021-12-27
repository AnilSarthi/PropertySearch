package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyAccountPage {
    @Autowired
    private WebDriverHelper webDriverHelper;

    private final By MY_SEARCHES_SELECTOR = By.linkText("Alerts & searches");
    private final By MY_ZOOPLA_SELECTOR = By.linkText("My Zoopla");

    public void clickOnAlertsAndSearches() {
        webDriverHelper.waitForElementToPresent(MY_ZOOPLA_SELECTOR);
        webDriverHelper.clickOnElement(MY_ZOOPLA_SELECTOR);
        webDriverHelper.waitForElementToPresent(MY_SEARCHES_SELECTOR);
        webDriverHelper.clickOnElement(MY_SEARCHES_SELECTOR);
    }



}
