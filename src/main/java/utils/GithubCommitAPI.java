package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GithubCommitAPI {

    public static String updateCsvFileInGithub(String base64Content, String filePath, String branch, String token, String repoName, String userName, String email) {
        String url = String.format("https://api.github.com/repos/%s/%s/contents/%s", userName, repoName, filePath);
        String message = "Updating CSV file";
        String sha = GithubGetShaAPI.getFileSHA(url, token);

        // JSON payload for the request
        String payload = String.format("{"
                + "\"message\": \"%s\", "
                + "\"committer\": {"
                + "\"name\": \"%s\", "
                + "\"email\": \"%s\"}, "
                + "\"content\": \"%s\", "
                + "\"sha\": \"%s\", "
                + "\"branch\": \"%s\"}", message, userName, email, base64Content, sha, branch);

        Response response = RestAssured.given()
                .header("Authorization", "token " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .put("https://api.github.com/repos/" + userName + "/" + repoName + "/contents/" + filePath);

        // Print the response body
        System.out.println("Update CSV Response Body: ");
        System.out.println("Response Code: " + response.statusCode());
        response.body().prettyPrint();

        return response.body().toString();
    }
}