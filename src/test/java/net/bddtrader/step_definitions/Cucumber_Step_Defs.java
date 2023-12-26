package net.bddtrader.step_definitions;

import io.cucumber.java.en.Given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import net.bddtrader.steps.Serenity_Reusable_Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;



public class Cucumber_Step_Defs {


//    @Given("Setting up the Base API URL")
//    public void set_Base_Url () {
//
//        RestAssured.baseURI = "http://localhost:9000/api"  ;
//    }




    @Given("ClientID is successfully created for the resource and Updated")
    public void client_id_is_successfully_created_for_the_resource() {

        Map<String, Object> map_json_values = new HashMap<>() ;
        map_json_values.put("firstName" , "Aadya");
        map_json_values.put("lastName" , "Singh") ;
        map_json_values.put("email" , "log2aadya@gmail.com")  ;

        SerenityRest.given()
                .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                .body(map_json_values) //pass the Map Json_values  for passing the methods variables
                .when()
                .post("http://localhost:9000/api/client")//give the final end point
                .then().and()// validate the response
                .statusCode(200)//validate the response status code
                .body("id" , not(equalTo(0)));//validate in the response we are able to get id as not equal 0
//                .and()
//                .body("email" , equalToIgnoringCase("log2aadya@gmail.com"))
//                //verify the value from the email type value
//                .and()
//                .body("firstName" , equalToIgnoringCase("Aadya")) ;

        Ensure.that("Email id is successfully verified" , response ->
                        response.body("email", Matchers.equalToIgnoringCase("log2aadya@gmail.com")))
                .andThat("FirstName is successfully verified" , response ->
                        response.body("firstName" ,Matchers.equalToIgnoringCase("Aadya")));
        
    }

}
