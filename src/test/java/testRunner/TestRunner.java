package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepDefinition"},
        plugin = {"json:target/cucumber.json"},
        publish = true,
        monochrome = true)

public class TestRunner {

}
