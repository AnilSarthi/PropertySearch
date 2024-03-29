package com.property;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Triggers all Cucumber tests in this package.
 * Both .feature and .java/.class files must be in the same package!
 * <p/>
 * Note: maven-failsage-reporting plugin requires this class to be suffixed with "IT".
 */
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, strict = true,
        tags= {"@smoke"},
        features = "src/test/resources/com/property",
        glue = {"com/property"},
        plugin = {"html:target/cucumber-pretty"}
)
public class RunIT {
}
