package utils;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class GithubMergeRequestAPI {
    public static void mergePullRequest(String pullRequestId, String commitMessage,String token, String userName, String repo) {
        Response response = given()
                .auth()
                .oauth2(token)
                .baseUri("https://api.github.com")
                .contentType(ContentType.JSON)
                .body("{\"commit_title\": \"" + commitMessage + "\", \"merge_method\": \"merge\"}")
                .put("/repos/" + userName + "/" + repo + "/pulls/" + pullRequestId + "/merge");

        System.out.println("Pull Request #" + pullRequestId);
        System.out.println("Response Code: " + response.statusCode());
        response.prettyPrint();
    }
}
