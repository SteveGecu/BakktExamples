package com.restassured.demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DemoOne {

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
