package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:target/cucumber-html-report.html"},
        features = "src/test/java/Features",
        glue= {"StepDefs"},
        monochrome = true,
        tags = "@lloyds"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
