package BDD_example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"BDD_example"},
        plugin = {
                "json:target/json-report/cucumber.json",
        },
        monochrome = true,
        dryRun = false,
        publish = false
)
public class Runner {
}
