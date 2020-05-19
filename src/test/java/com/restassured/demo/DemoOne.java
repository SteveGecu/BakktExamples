package com.restassured.demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DemoOne {

    @Test
    @DisplayName("Validating status code and extracting payload")
    public void Test1(){
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

    @Test()
    @DisplayName("Extracting id, name and company name from first query")
    public void Test2(){
        String URL = "https://gecho.gigalixirapp.com/api/SteveGecu/FirstSchema/query";
        String query="{\"query\":\"{\\n    getAuthors {\\n        id\\n        name\\n        " +
                "company\\n    }\\n}\",\"variables\":{}}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(query)
                .when()
                        .post(URL);

        Assert.assertEquals(200,response.statusCode());

        //extracting as Map
        Map<Object,Object> firstSchema = response.as(Map.class);

        //System.out.println("firstSchema = " + firstSchema);

        String companyName = response.jsonPath().getString("data.getAuthors[0].company");
        String name = response.jsonPath().getString("data.getAuthors[0].name");
        String idNumber = response.jsonPath().getString("data.getAuthors[0].id");
        System.out.println("idNumber is= " + idNumber);
        System.out.println("name is= " + name);
        System.out.println("companyName is= " + companyName);

    }

    @Test
    @DisplayName("Extracting all first names from query in to a Java list")
    public void Test3(){
        String URL ="https://gecho.gigalixirapp.com/api/ascendum-qa/students/query";
        String query = "{\"query\":\"{\\n    greeting\\n    students " +
                "{\\n        firstName\\n        lastName\\n        email\\n        " +
                "SAT\\n        city\\n        state\\n    }\\n    \\n}\",\"variables\":{}}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(query)
                .when()
                        .post(URL);
        Assert.assertEquals(200,response.statusCode());

        response.prettyPeek();
        List<Object> nameList = response.jsonPath().getList("data.students.firstName");
        System.out.println("nameList = " + nameList);
    }
}
