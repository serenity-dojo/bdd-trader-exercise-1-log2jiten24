package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class Basic_Auth_token {

    @Before
    public void setupBaseURL () {

        RestAssured.baseURI = "http://localhost:9000/api"  ;
    }

    @Test
    public void basic_auth_token_authentication_examples () {

        //create a HashMap passing the key and value pair as JSON and pass it over the body - forming the JSON
        //Request with the key and value pair

        Map<String, Object> map_json_values = new HashMap<>() ;
        map_json_values.put("firstName" , "Aadya");
        map_json_values.put("lastName" , "Singh") ;
        map_json_values.put("email" , "log2aadya@gmail.com")  ;

        RestAssured.given()
                .auth().basic("user" , "password")//passing the basic authentication
                .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                .body(map_json_values) //pass the Map Json_values  for passing the methods variables
                .when()
                .post("/client")//give the final end point
                .then().and()// validate the response
                .statusCode(200)//validate the response status code
                .body("id" , not(equalTo(0)))//validate in the response we are able to get id as not equal 0
                .and()
                .body("email" , equalToIgnoringCase("log2aadya@gmail.com"))
                //verify the value from the email type value
                .and()
                .body("firstName" , equalToIgnoringCase("Aadya")) ;


    }
}
