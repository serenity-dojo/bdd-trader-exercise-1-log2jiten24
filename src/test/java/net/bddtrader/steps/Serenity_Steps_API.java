package net.bddtrader.steps;
import net.bddtrader.clients.Client;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

public class Serenity_Steps_API  {

    private String actor ;

    @Step("actor registers a new client {0}")
    public String  generate_StringId (Client new_Client) {

         SerenityRest.given()
                     .contentType(ContentType.JSON) //give the exact content type as headers
                     //.body(new_client) //pass the String as the body request for the POST Method
                    .body(new_Client) //pass the POJO class object for passing the methods variables
                    .when()
                    .post("/client")//give the final end point
                    .then().statusCode(200);
        Ensure.that("Fetch the ID value from the Response" , validatableResponse ->
                validatableResponse.body("id" , Matchers.not(equals(0))));
        //fetch the id from the Response String
        return SerenityRest.lastResponse().jsonPath().getString("id");

    }

    @Step("actor searches for a client with id {0}")
    public void searchForClient_ID (String clientID) {

        SerenityRest.given()
                .pathParam("id" , clientID)
                .get("/client/{id}" ,clientID)
                .then()
                .statusCode(200) ;
    }

    @Step("actor finds the client matching {0}")
    public void findAllClientMatching (Client expectClient) {

        Ensure.that("FirstName value matching"  + expectClient.getFirstName(),
                validateResponse -> validateResponse.body("firstName" ,
                        Matchers.equalToIgnoringCase(expectClient.getFirstName())))
                .andThat("LastName value matching"  + expectClient.getLastName(),
                        validateResponse -> validateResponse.body("lastName" ,
                                Matchers.equalToIgnoringCase(expectClient.getLastName())))
                .andThat("Email value matching" + expectClient.getEmail(),
                        validateResponse -> validateResponse.body("email" ,
                                Matchers.equalToIgnoringCase(expectClient.getEmail()))) ;
    }
}
