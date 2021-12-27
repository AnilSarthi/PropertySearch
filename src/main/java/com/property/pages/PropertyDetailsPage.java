package com.property.pages;

import com.property.util.WebDriverHelper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PropertyDetailsPage {
    private final By SEARCH_RESULTS_AREA_SELECTOR = By.cssSelector("[data-testid='listing-details-image-link']");
    private final By PROPERTY_FEATURES = By.cssSelector("[data-testid='listing_features_bulletted']");
    private final By PROPERTY_FEATURES1 = By.cssSelector(".dp-features");
    private final By PROPERTY_DETAILS_FEATURES = By.cssSelector("[data-testid='truncated_text_container']");
    private final By PROPERTY_DETAILS_FEATURES1 = By.cssSelector(".dp-description__text");

    @Autowired
    private WebDriverHelper webDriverHelper;

    public Map<String, Boolean> getPropertiesHasSearchTerm(String searchTerm) {
        Map<String, Boolean> results = new HashMap<>();
        getAllSearchResults().stream().forEach(url -> {
            boolean isFound = false;
            webDriverHelper.navigateToURL(url);
            if (webDriverHelper.getText(getSelector(Arrays.asList(PROPERTY_FEATURES,PROPERTY_FEATURES1))).toLowerCase().contains(searchTerm)) {
                isFound = true;
                } else if (webDriverHelper.getText(getSelector(Arrays.asList(PROPERTY_DETAILS_FEATURES,PROPERTY_DETAILS_FEATURES1))).toLowerCase().contains(searchTerm)) {
                isFound = true;
            }
            if(isFound==false){
                System.out.println("");
            }
            results.put(url, isFound);
        });
        return results;
    }

    private List<String> getAllSearchResults() {
        webDriverHelper.waitForElementToPresent(SEARCH_RESULTS_AREA_SELECTOR);
        return webDriverHelper.getAttributeValues(SEARCH_RESULTS_AREA_SELECTOR, "href");
    }
    public By getSelector(List<By> locatorsList){
        for(By locator:locatorsList){
            webDriverHelper.waitForElementToPresent(locator,1);
            if(webDriverHelper.isElementDisplayed(locator)){
                return locator;
            }
        }
        return locatorsList.get(0);
    }

}
