package net.bddtrader.serenity_exercise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@RunWith(SerenityRunner.class) //Run an Existing Rest Assured Project in Serenity Project
public class Serenity_Rest_PUT_Update_Request_Resource {

    @Before
    public void setupBaseURL () {

        RestAssured.baseURI = "http://localhost:9000/api"  ;

    }

    @Test
    public void update_resource_client () {

        //creating object of the Client POJO class - and passing all the values using the POJO Class Methods
        //using the builder pattern to call all the methods from the POJO class
        Client client_pojo = Client.withFirstName("Priya").andLastName("Singh").andEmail("log2priya12july@gmail.com") ;
        //Retrieve the Client ID from the POST API Response
        String client_id =  SerenityRest.given()
                                             .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                                            .body(client_pojo) //pass the POJO class object for passing the methods variables
                                            .when()
                                            .post("/client")//give the final end point
                                            .jsonPath().getString("id") ;//get the Client ID from the Response

        // To update the specific resource using the PUT method
        //update the POJO class with new resource update using the PUT Resource
        Client client_pojo_update = Client.withFirstName("Aadya").andLastName("Singh").andEmail("log2aadya12july@gmail.com") ;

                             SerenityRest.given()
                                         //.pathParam("id" , client_id)
                                         .contentType(ContentType.JSON) //pass the content type
                                         .body(client_pojo_update) // pass the POJO - updated resource using PUT method
                                         .put("/client/{client_id}", client_id)//forming the final URL
                                         // and passing Id without using the PathParam
                                         .then()
                                         .statusCode(200) ;//verify the code status code

        //After the update - retrieve  the resource using the GET Request ID to retrieve
                             SerenityRest.given()
                                     .contentType(ContentType.JSON)
                                     .when()
                                     .get("client/{client_id}" , client_id)
                                     .then()
                                     .statusCode(200);//validate the status code
                                        // .body("email" , Matchers.equalToIgnoringCase("log2aadya12july@gmail.com"));
                                //verify the response body having the updated email ID
        //Adding the Serenity Assertions -Ensurethat () - to verify whether the email body is present or not
        Ensure.that("Email is successfully updated to the updated value" , validatableResponse ->
                validatableResponse.body("email", Matchers.equalToIgnoringCase("log2aadya12july@gmail.com")));
    }


    @Test
    public void put_resource_client_reusable_method () {

        //creating object of the Client POJO class - and passing all the values using the POJO Class Methods
        //using the builder pattern to call all the methods from the POJO class
        Client client_pojo = Client.withFirstName("Priyaaa").andLastName("Singh").andEmail("log2priyaa12july@gmail.com") ;
        //Retrieve the Client ID from the POST API Response
        String client_id = getId(client_pojo);//get the Client ID from the Response from the reusable method
        //create a method which will be used to pass the POJO Class methods

        // To update the specific resource using the PUT method
        //update the POJO class with new resource update using the PUT Resource
        Client client_pojo_update = Client.withFirstName("Aadya").andLastName("Singh").andEmail("log2aadyaaa12july@gmail.com") ;

        SerenityRest.given()
                //.pathParam("id" , client_id)
                .contentType(ContentType.JSON) //pass the content type
                .body(client_pojo_update) // pass the POJO - updated resource using PUT method
                .put("/client/{client_id}", client_id)//forming the final URL
                // and passing Id without using the PathParam
                .then()
                .statusCode(200) ;//verify the code status code

        // without the pathParam

        //After the update - retrieve  the resource using the GET Request ID to retrieve
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .get("client/{client_id}" , client_id)
                .then()
                .statusCode(200);//validate the status code
               // .body("email" , Matchers.equalToIgnoringCase("log2aadyaaa12july@gmail.com"));
        //verify the response body having the updated email ID
        Ensure.that("Email ID is Updated with correct Email ID " , validatableResponse ->
                validatableResponse.body("email" , Matchers.equalToIgnoringCase("log2aadyaaa12july@gmail.com")));



    }


    @Test
    public void put_resource_client_reusable_method_map () {

        //creating object of the Client POJO class - and passing all the values using the POJO Class Methods
        //using the builder pattern to call all the methods from the POJO class
        Client client_pojo = Client.withFirstName("Priyaaa").andLastName("Singh").andEmail("log2priyaa12july@gmail.com") ;
        //Retrieve the Client ID from the POST API Response
        String client_id = getId(client_pojo);//get the Client ID from the Response from the reusable method
        //create a method which will be used to pass the POJO Class methods

        // To update the specific resource using the PUT method
        //Update using the Map - resource to update the multiple JSON values
        Map<String, Object> json_details = new HashMap<String , Object>() ;
        json_details.put("FirstName" , "Kumar") ;
        json_details.put("LastName" , "Jitu") ;
        json_details.put("email" , "log2jiten23@gmail.com") ;

        SerenityRest.given()
                //.pathParam("id" , client_id)
                .contentType(ContentType.JSON) //pass the content type
                .body(json_details) // pass the Map details to update the resource
                .put("/client/{client_id}", client_id)//forming the final URL
                // and passing Id without using the PathParam
                .then()
                .statusCode(200) ;//verify the code status code



        //After the update - retrieve  the resource using the GET Request ID to retrieve
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .get("client/{client_id}" , client_id)
                .then()
                .statusCode(200);//validate the status code
                //.body("email" , Matchers.equalToIgnoringCase("log2jiten23@gmail.com"));
        //verify the response body having the updated email ID

        Ensure.that("Email ID is successfully updated" , validatableResponse ->
                validatableResponse.body("email", Matchers.equalToIgnoringCase("log2jiten23@gmail.com")));


    }

    /*
    Creating the POJO Class Reusable Method - for creating the unique id
    Input Parameter - POJO Class
     */
    private String getId(Client client_pojo) {

        return SerenityRest.given()
                .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                .body(client_pojo) //pass the POJO class object for passing the methods variables
                .when()
                .post("/client")//give the final end point
                .jsonPath().getString("id");
    }
}
