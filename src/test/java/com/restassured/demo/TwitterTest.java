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

    @Test
    public void Test2(){
        String URL = "https://gecho.gigalixirapp.com/api/SteveGecu/FirstSchema/query";
        String query="{\"query\":\"{\\n    getAuthors {\\n        id\\n        name\\n        company\\n    }\\n}\",\"variables\":{}}";


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
    public void Test3(){
        String URL = "https://gecho.gigalixirapp.com/api/SteveGecu/FirstSchema/query";
        String query="{\"query\":\"{\\n    getAuthors {\\n        id\\n        name\\n        company\\n    }\\n}\",\"variables\":{}}";

        Response response =
        given()
                .contentType(ContentType.JSON)
                .body(query)
        .when()
                .post(URL);

        Map<Object,Object> firstSchema = response.as(Map.class);
        //System.out.println("firstSchema = " + firstSchema);
        //System.out.println(firstSchema.get("data"));
        System.out.println(response.jsonPath().getString("data.getAuthors[0].id"));

    }


}
