package com.property.context;

import com.property.browser.BrowserDriver;
import com.property.browser.BrowserFactory;
import com.property.util.WebDriverHelper;
import cucumber.api.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Context {
    private Scenario scenario;
    @Autowired
    private BrowserFactory browserFactory;

    private BrowserDriver browserDriver;

    @Value("${test.url}")
    private String baseUrl;

    @Autowired
    private WebDriverHelper webDriverHelper;

    public void setUp(Scenario scenario) {
        System.out.println("Context.setup() called with scenario: " + scenario);
        this.scenario = scenario;
        this.browserDriver = browserFactory.create();
    }

    public void tearDown() {
        this.browserDriver.quit();
    }

    public BrowserDriver getBrowser() {
        return this.browserDriver;
    }

    public Scenario getScenario() {
        return this.scenario;
    }

    public void afterScenarioFailedReport(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario Failed - "+scenario.getName());
        }
        webDriverHelper.screenshot();
    }
}
