package com.property.pages;

import com.property.model.User;
import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterPage {
    private final By EMAIL_SELECTOR = By.id("input-email-address");
    private final By PASSWORD_SELECTOR = By.id("input-password");
    private final By REGISTER_SELECTOR = By.cssSelector("button[data-testid='register-button']");
    private final By CONSENT_YES_SELECTOR = By.cssSelector("[data-testid='label-consent__insights__24-yes']");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void registerAnUser(User user) {
        webDriverHelper.waitForElementToPresent(EMAIL_SELECTOR);
        webDriverHelper.setTextInTextbox(EMAIL_SELECTOR, user.getEmailAddress());
        webDriverHelper.setTextInTextbox(PASSWORD_SELECTOR, user.getPassWord());
        webDriverHelper.clickOnElement(CONSENT_YES_SELECTOR);
        webDriverHelper.clickOnElement(REGISTER_SELECTOR);
    }
}
