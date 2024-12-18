package runner;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.core.options.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@credencialesInvalidas")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepsTest")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber-reports/cucumber.json")
public class CucumberTest {
}