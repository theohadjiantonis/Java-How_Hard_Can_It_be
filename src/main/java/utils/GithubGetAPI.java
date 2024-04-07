package utils;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.equalTo;

public class GithubGetAPI {

    public static String getFileSHA(String url, String token) {

        // Make the REST API call to GitHub to get the file details
        ValidatableResponse response = RestAssured.given()
                .header("Authorization", "token " + token)
                .when()
                .get(url)
                .then()
                .statusCode(200);

        // Extract the SHA from the response
        String sha = response.extract().path("sha");

        // Print the response body
        System.out.println("Retrieve Current Sha for Companies.csv");
        return sha;
    }

    public static String getFile(String userName, String token, String repoName, String filePath, String branch) {

        // Make the REST API call to GitHub to get the file details
        ValidatableResponse response = RestAssured.given()
                .header("Authorization", "token " + token)
                .when()
                .get(String.format("https://api.github.com/repos/%s/%s/contents/%s?ref=%s", userName, repoName, filePath, branch))
                .then()
                .statusCode(200);

        System.out.println("Retrieve " + filePath + " from " + branch);
        return response.extract().path("content");
    }
}