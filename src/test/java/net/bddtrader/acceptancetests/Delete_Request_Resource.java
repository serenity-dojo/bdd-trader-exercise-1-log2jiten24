package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class Delete_Request_Resource {

    @Before
    public void setupBaseURL () {

        RestAssured.baseURI = "http://localhost:9000/api"  ;
    }

    @Test
    public void delete_resource_client () {

        //creating object of the Client POJO class - and passing all the values using the POJO Class Methods
        //using the builder pattern to call all the methods from the POJO class
        Client client_pojo = Client.withFirstName("Priya").andLastName("Singh").andEmail("log2priya12july@gmail.com") ;
        //Retrieve the Client ID from the POST API Response
        String client_id =  RestAssured.given()
                                             .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                                            .body(client_pojo) //pass the POJO class object for passing the methods variables
                                            .when()
                                            .post("/client")//give the final end point
                                            .jsonPath().getString("id") ;//get the Client ID from the Response

        //When I delete the client resource
                         RestAssured.given()
                                           //.pathParam("id" , client_id)//giving the id as pathParam
                                           .delete("/client/{client_id}" , client_id) ;//delete the existing resource
                                           // without the pathParam

        //To verify the Client Resource - whether the resource is existing or not
                         RestAssured.given()
                                         //.pathParam("id" , client_id)
                                         .get("/client/{client_id}", client_id)//retrieve the existing resource
                                         // and passing Id without using the PathParam
                                         .then()
                                         .statusCode(404) ;//verify the code status exist or not



    }


    @Test
    public void delete_resource_client_reusable_method () {

        //creating object of the Client POJO class - and passing all the values using the POJO Class Methods
        //using the builder pattern to call all the methods from the POJO class
        Client client_pojo = Client.withFirstName("Priya").andLastName("Singh").andEmail("log2priya12july@gmail.com") ;
        //Retrieve the Client ID from the POST API Response
        String client_id = getId(client_pojo);//get the Client ID from the Response from the reusable method
        //create a method which will be used to pass the POJO Class methods
        //When I delete the client resource
        RestAssured.given()
                //.pathParam("id" , client_id)//giving the id as pathParam
                .delete("/client/{client_id}" , client_id) ;//delete the existing resource
        // without the pathParam

        //To verify the Client Resource - whether the resource is existing or not
        RestAssured.given()
                //.pathParam("id" , client_id)
                .get("/client/{client_id}", client_id)//retrieve the existing resource
                // and passing Id without using the PathParam
                .then()
                .statusCode(404) ;//verify the code status exist or not



    }

    /*
    Creating the POJO Class Reusable Method - for creating the unique id
    Input Parameter - POJO Class
     */
    private String getId(Client client_pojo) {

        return RestAssured.given()
                .contentType(ContentType.JSON) //give the exact content type as headers
                //.body(new_client) //pass the String as the body request for the POST Method
                .body(client_pojo) //pass the POJO class object for passing the methods variables
                .when()
                .post("/client")//give the final end point
                .jsonPath().getString("id");
    }
}
