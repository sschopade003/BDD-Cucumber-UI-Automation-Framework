package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//@RunWith(Cucumber.class)
//plugin = {"pretty","html:target/cucumber-reports/reports_html.html"}

@CucumberOptions(features = { "Feature/Login.feature",
"Feature/Home.feature" }, 
glue = "StepDefination", 
dryRun = false, 
monochrome = true, 
tags = "@Regression or @Sanity", // scenarios under @tag will be executed
plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
