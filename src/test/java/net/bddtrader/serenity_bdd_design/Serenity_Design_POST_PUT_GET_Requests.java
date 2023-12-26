package net.bddtrader.serenity_bdd_design;

import io.restassured.RestAssured;
import net.bddtrader.clients.Client;
import net.bddtrader.steps.Serenity_Reusable_Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Serenity_Design_POST_PUT_GET_Requests {


    @Before
    public void set_Base_Url () {

        RestAssured.baseURI = "http://localhost:9000/api"  ;
    }

    @Steps
    Serenity_Reusable_Steps serenity_reus_steps_obj ;

    @Test
    public void each_client_update_clientID () {

        //Form the JSON Request using the POJO Methods
        Client client_pojo = Client.withFirstName("Priya").andLastName("Singh").andEmail("log2priya12july@gmail.com") ;
        //get the ID by performing the POST Request
        String client_ID = serenity_reus_steps_obj.generate_new_ClientID(client_pojo) ;
        //pass the POJO methods forming the JSON Request

        //Update the resource with new payload
        Client new_pojo_client_obj = Client.withFirstName("Kumar").andLastName("Jeet").andEmail("log2jiten24@gmail.com") ;
        //Update the client with new Resource using the PUT Method
        String update_client_id = serenity_reus_steps_obj.update_resource_ClientID(client_pojo, new_pojo_client_obj);

        //Perform the GET -Retrieve Query to retrieve  the Results
        serenity_reus_steps_obj.searchForClient_ID_Get_Request(update_client_id);

        //Perform all the Assertion Steps -and verify each of the field is retrieved successfully or not
        serenity_reus_steps_obj.findAllClientMatching(new_pojo_client_obj);
        //Pass the Client_POJO - details for performing the assertion steps


    }


}
