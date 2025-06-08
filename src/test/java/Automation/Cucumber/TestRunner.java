package Automation.Cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/java/Automation/Cucumber",glue="Automation.StepDefinition",monochrome=true,tags="@ErrorValidation",plugin= {"html:target/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests{

}
