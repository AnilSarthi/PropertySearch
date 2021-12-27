package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateAlertAndSearchesPage {
    private final By SAVE_SEARCHES_SELECTOR = By.cssSelector(".btn.btn-primary");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void setAlertAndSave(String alertType) {
        String ALERT_FREQUENCY = "frequency_";
        By id = By.id(ALERT_FREQUENCY + 0);
        webDriverHelper.waitForElementToPresent(id);

        switch (alertType.toUpperCase()) {
            case "DAILY SUMMARY EMAILS":
                webDriverHelper.clickOnElement(By.id(ALERT_FREQUENCY + 1));
                break;
            case "INSTANT PROPERTY ALERTS":
                webDriverHelper.clickOnElement(id);
                break;
            default:
                throw new RuntimeException("Invalid alert selected " + alertType);
        }
        webDriverHelper.clickOnElement(SAVE_SEARCHES_SELECTOR);
    }

}
