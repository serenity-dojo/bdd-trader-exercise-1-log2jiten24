package net.bddtrader.serenity_bdd_design;

import io.restassured.RestAssured;
import net.bddtrader.clients.Client;
import net.bddtrader.steps.Serenity_Steps_API;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Serenity_Rest_Design {

    @Before
    public void set_Base_Url () {
        RestAssured.baseURI = "http://localhost:9000/api"  ;
    }


    @Steps
    Serenity_Steps_API serenity_steps ;

    @Test
    public void each_client_give_unique_id () {
        //Form the JSON Request using the POJO Methods
        Client client_pojo = Client.withFirstName("Priya").andLastName("Singh").andEmail("log2priya12july@gmail.com") ;

        //get the ID by post the query
        String client_ID = serenity_steps.generate_StringId(client_pojo) ;//pass the POJO methods forming the JSON Request
        //Once we get the ID - search for the client using the Client_ID
        serenity_steps.searchForClient_ID(client_ID);

        //after we retrieve the Id lets verify for the value is successfully retreived in the response or not
        serenity_steps.findAllClientMatching(client_pojo);
    }
}
