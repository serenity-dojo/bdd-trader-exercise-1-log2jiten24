package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PathParameter_Get_Request {


    @Before
    public void prepare_rest_config () {

        //set the base URI
        RestAssured.baseURI = "http://localhost:9000/api" ;

    }

    @Test
    public void should_return_name_and_sector_refactor_01()
    {
     //giving the Path Parameter to the Get Request - for forming the Get End Point
        RestAssured.
                given()
                        .pathParam("symbol" , "aapl")
                .when()
                .get("/stock/{symbol}/company")
                .then()
                .body("companyName", equalTo("Apple, Inc."))
                .body("sector", equalTo("Electronic Technology"));
    }



}
