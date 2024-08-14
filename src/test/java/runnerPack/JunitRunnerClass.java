package runnerPack;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/apiFeatures/MapsPlaceValidation.feature",plugin = "json:target/jsonReports/cucumber-reporting.json", glue = {
		"stepDefinitionsPack" }, tags = "@addPlace or @deletePlace")
public class JunitRunnerClass {

	
}
