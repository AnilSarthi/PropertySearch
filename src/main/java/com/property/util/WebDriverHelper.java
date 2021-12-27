package com.property.util;

import com.property.context.Context;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebDriverHelper {
    @Autowired
    private Context context;


    public void navigateToURL(final String url) {
        context.getBrowser().get(url);
    }

    public void clickOnElement(final By locator) {
        context.getBrowser().findElement(locator).click();
    }

    public boolean isElementDisplayed(final By locator) {
        try {
            return context.getBrowser().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectValueFromDropdown(final By locator, final String selectingValue) {
        if (selectingValue != null & !selectingValue.trim().equals("")) {
            final WebElement option = context.getBrowser().findElement(locator);
            final Select selectOption = new Select(option);
            selectOption.selectByVisibleText(selectingValue);
        }

    }

    public void setTextInTextbox(final By locator, final String text) {
        context.getBrowser().findElement(locator).clear();
        context.getBrowser().findElement(locator).sendKeys(text);
    }

    public String getText(final By locator) {
        return context.getBrowser().findElement(locator).getText();
    }

    public void waitForElementToPresent(final By element, final int waitTimeInSeconds) {
        try {
            (new WebDriverWait(context.getBrowser(), waitTimeInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(element));
        } catch (TimeoutException te) {
//            if catches time out exception do nothing

        }
    }

    public void waitForElementToPresent(final By element) {
        waitForElementToPresent(element, 10);
    }

    public void screenshot() {
        byte[] screenshot = context.getBrowser().getScreenshotAs(OutputType.BYTES);
        context.getScenario().embed(screenshot, "image/png");
    }

    public void refreshCurrentPage() {
        context.getBrowser().navigate().refresh();
    }

    public List<String> getAttributeValues(By locator, String attribute) {
        return context.getBrowser().findElements(locator).stream().map(element -> element.getAttribute(attribute)).collect(Collectors.toList());
    }
}
