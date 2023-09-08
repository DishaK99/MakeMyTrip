package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( features="src\\test\\java\\features\\GiftCards.feature",
glue="stepDefinitions",
plugin={"pretty","html:target/Cucumber-report1.html",
	    "junit:target/JUNITReports1/log1.xml",
	    "json:target/JSONReports1/log1.json",
	    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
})
public class GiftcardsRunner {

}
