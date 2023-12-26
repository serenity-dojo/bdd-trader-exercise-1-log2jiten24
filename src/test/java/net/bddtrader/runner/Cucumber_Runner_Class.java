package net.bddtrader.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"} ,
        features = "src/test/resources/feature_files/Cucumber_Sample_RestAPI.feature",
        glue = "net.bddtrader.step_definitions"


)
public class Cucumber_Runner_Class {


}
