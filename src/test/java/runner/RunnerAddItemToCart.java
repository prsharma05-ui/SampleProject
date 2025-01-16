package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/additemtocart", glue = "stepdefinitionadditemtocart", plugin = {
		"pretty", "html:target/cucumber-reports.html" } // Report generation
)
public class RunnerAddItemToCart {
}