package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class Query_Parameter_Code_Get_Request {


    @Before
    public void prepare_rest_config () {

        //set the base URI
        RestAssured.baseURI = "https://bddtrader.herokuapp.com/api" ;

    }

    @Test
    public void  should_return_news_for_a_requested_company_first()
    {
        //giving the Query  Parameter to the Get Request - for forming the Get End Point
        RestAssured.
                given()
                .queryParam("symbols", "fb") //passing the Query Parameter to the Get Request End Point
                .when()
                .get("/news")
                .then()
                .body("related" , everyItem(containsString("FB")));//in the response body with the key
        //as related - we will get the value assosciated with that key and assert the values inside the list

    }

    @Test
    public void  should_return_news_for_a_requested_company_second()
    {
        //giving the Query  Parameter to the Get Request - for forming the Get End Point
        RestAssured.
                given()
                .queryParam("symbols", "AAPL") //passing the Query Parameter to the Get Request End Point
                .when()
                .get("/news")
                .then()
                .body("related" , everyItem(containsString("AAPL")));//in the response body with the key
        //as related - we will get the value assosciated with that key and assert the values inside the list

    }
}
