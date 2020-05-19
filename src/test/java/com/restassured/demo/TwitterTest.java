package com.restassured.demo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;


public class TwitterTest {

    String URL = "https://gecho.gigalixirapp.com/api/ascendum-qa/twitter/query";

    @Before
    public void setUp() {}

    @Test
    public void GetTweet() {
        String query =
                "{\"query\":\"{\\n    Tweet(id: 1) {\\n        body\\n    }\\n}\",\"variables\":{}}";

        given()
                .contentType(ContentType.JSON)
                .body(query)
        .when()
                .post(URL)
                .prettyPeek()
        .then()
                .statusCode(200);
    }

    @Test
    public void Test1(){
        Response response = post(URL);
        response.prettyPeek();
        String msj = response.jsonPath().getString("errors[0].message");
        System.out.println("msj is = " + msj);
    }




}
