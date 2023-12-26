package net.bddtrader.acceptancetests;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class JsonPath_Exercise_Practice {

    @Before
    public void prepare_rest_config() {
        baseURI = "http://localhost:9000/api";
    }

    /**
     * Exercise 1 - Read a single field value
     * Complete the test shown below to read and verify the "industry" field for AAPL
     * The result should be 'Telecommunications Equipment'
     */
    @Test
    public void find_a_simple_field_value() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/company")
                .then()
                .body("industry" , Matchers.equalToIgnoringCase("Telecommunications Equipment"));
                 //verify the Response with the value for the Industry Code values
    }

    /**
     * Exercise 2 - Read a single field value
     * Complete the test shown below to read and verify the "description" field for AAPL
     * The result should contain the string 'smartphones'
     */
    @Test
    public void check_that_a_field_value_contains_a_given_string() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/company")
                .then()
                .body("description" , containsString("smartphones"));
        //verify the Response with the value for the Industry Code values
        //to verify the following text is present inside the string or not
    }


    /**
     * Exercise 3 - Read a single nested field value
     * Read the 'symbol' field inside the 'quote' entry in the Apple stock book (https://bddtrader.herokuapp.com/api/stock/aapl/book)
     * The result should be 'AAPL'
     */
    @Test
    public void find_a_nested_field_value() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                .body("quote.symbol" , equalToIgnoringCase("AAPL") );
        //verifying the nested json value response
    }


    /**
     * Exercise 4 - Find a list of values
     * Find the list of symbols recently traded from https://bddtrader.herokuapp.com/api/tops/last and
     * check that the list contains "PTN", "PINE" and "TRS"
     */
    @Test
    public void find_a_list_of_values() {

        //matching the list of values inside the list
        when().get("/tops/last") //give the end point as - http://localhost:9000/api/tops/last
                .then().body("symbol" ,hasItems("PIN", "IYF" , "PVTL"));//verify the list of Items is present
                                //give the JSON Key value
    }

    /**
     * Exercise 5 - check that at least one item in a list matches a certain condition
     * Check that there is at least one price that is greater than 100.
     */
    @Test
    public void make_sure_at_least_one_item_matches_a_given_condition() {

        //matching the list of values inside the list
        when().get("/tops/last") //give the end point as - http://localhost:9000/api/tops/last
                .then().body("symbol" ,hasItems(greaterThan("100.3f")));//verify the list of Items is present
                            //verify that any of the price is greater than 100.3 price

    }

    /**
     * Exercise 6 - check the value of a specific item in a list
     * Check that price of the first trade in the Apple stock book is 319.59
     */
    @Test
    public void find_a_field_of_an_element_in_a_list() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                .body("trades[0].price" , equalTo(319.59f));
                 //verify the price of the trades - the first items assertion
    }

    /**
     * Exercise 7 - check the value of a specific item in a list
     * Check that price of the last trade in the Apple stock book is 319.54
     */
    @Test
    public void find_a_field_of_the_last_element_in_a_list() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                .body("trades[-1].price" , equalTo(319.54f));
        //verify the price of the trades - the last items price assertion

    }

    /**
     * Exercise 8 - check for number of elements in a collection
     * Check that there are 20 trades
     */
    @Test
    public void find_the_number_of_trades() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                .body("trades.size()" , equalTo(20) ) ;
        //find the list of the number of items - the size of the number of trades list
    }

    /**
     * Exercise 9 - check for minimum or maximum
     * Check that the minimum price of any trade in the Apple stock book is 319.38
     */
    @Test
    public void find_the_minimum_trade_price() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                .body("trades.price.min()" , equalTo(319.38f) ) ;
        //find the list for the minimum trade price

    }

    /**
     * Exercise 10 - find a value using closure expressions
     * Check that the volume of the trade with the minimum price is 100
     */
    @Test
    public void find_the_size_of_the_trade_with_the_minimum_trade_price() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then()
                //.body("trades.min{ trade -> trade.price}.size" , equalTo(100.0f) ) ;
                //replace the trade.price with it statement
                .body("trades.min{it.price}.size" , equalTo(100.0f));

    }

    /**
     * Exercise 11 - find a collection of objects matching a specified criteria
     * Check that there are 13 trades with prices greater than 319.50
     */
    @Test
    public void find_the_number_of_trades_with_a_price_greater_than_some_value() {

        given().pathParam("symbol" , "aapl")
                .when().get("/stock/{symbol}/book")
                .then().body("trades.findAll{it.price > 319.50}.size()" ,equalTo(13)) ;
        //.body("trades.min{ trade -> trade.price}.size" , equalTo(100.0f) ) ;
        //findAll method to match the price
        //replace the trade.price with it statement
    }

}