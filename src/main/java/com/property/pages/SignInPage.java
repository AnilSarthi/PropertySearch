package com.property.pages;

import com.property.model.User;
import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInPage {
    private final By SIGN_IN_EMAIL_SELECTOR = By.id("signin_email");
    private final By SIGN_IN_PASSWORD_SELECTOR = By.id("signin_password");
    private final By SIGIN_IN_SUBMIT_SELECTOR = By.id("signin_submit");
    @Autowired
    private WebDriverHelper webDriverHelper;

    public void login(User user) {
        webDriverHelper.waitForElementToPresent(SIGN_IN_EMAIL_SELECTOR);
        webDriverHelper.setTextInTextbox(SIGN_IN_EMAIL_SELECTOR, user.getEmailAddress());
        webDriverHelper.setTextInTextbox(SIGN_IN_PASSWORD_SELECTOR, user.getPassWord());
        webDriverHelper.clickOnElement(SIGIN_IN_SUBMIT_SELECTOR);
    }


}
