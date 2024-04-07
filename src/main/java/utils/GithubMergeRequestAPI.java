package utils;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class GithubMergeRequestAPI {
    public static void mergePullRequest(Integer pullRequestId, String commitMessage,String token, String userName, String repo) {
        ValidatableResponse response = given()
                .auth()
                .oauth2(token)
                .baseUri("https://api.github.com")
                .contentType(ContentType.JSON)
                .body("{\"commit_title\": \"" + commitMessage + "\", \"merge_method\": \"merge\"}")
                .when()
                .put("/repos/" + userName + "/" + repo + "/pulls/" + pullRequestId + "/merge")
                .then()
                .statusCode(200);

        System.out.println("Pull Request #" + pullRequestId);
        System.out.println("Response Code: " + response.extract().statusCode());
    }
}
