package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetCompany_Details_Refactor_Code {

    @Before
    public void prepare_rest_config () {

        //set the base URI
        RestAssured.baseURI = "http://localhost:9000/api" ;

    }

    @Test
    public void should_return_name_and_sector_refactor()
    {

        RestAssured.get("/stock/aapl/company")
                .then()
                .body("companyName", equalTo("Apple, Inc."))
                .body("sector", equalTo("Electronic Technology"));
    }
}
