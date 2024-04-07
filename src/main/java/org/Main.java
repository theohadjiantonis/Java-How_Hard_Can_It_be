package org;

import GithubAPIs.*;
import modelClasses.Company;
import utils.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //generate a list with company objects
        List<Company> companiesList = GenerateRecords.generateCompanies(10);

        //generate the CSV content from the list of Company objects
        String csvContent = Converters.convertListToCSV(companiesList);

        // Convert the CSV content to Base64, so it can be sent using the API call
        String base64Content = Base64.getEncoder().encodeToString(csvContent.getBytes(StandardCharsets.UTF_8));

        // Make a REST API call to GitHub to commit the changes
        GithubCommitAPI.updateCsvFileInGithub(base64Content, "Companies.csv", "main", staticRepository.AUTHTOKEN, staticRepository.REPONAME, staticRepository.USERNAME, staticRepository.EMAIL);

        //Get file from feature and main branch and assert that they are different
        String featureBranchFileContents = Converters.convertBase64ToCSV(GithubGetAPI.getFile(staticRepository.USERNAME, staticRepository.AUTHTOKEN, staticRepository.REPONAME, "Companies.csv", "feature-branch"));
        String mainBranchFileContents = Converters.convertBase64ToCSV(GithubGetAPI.getFile(staticRepository.USERNAME, staticRepository.AUTHTOKEN, staticRepository.REPONAME, "Companies.csv", "main"));

        //assert that the two strings from the CSV files in main and feature are different after commiting the changes to main
        assert !featureBranchFileContents.equals(mainBranchFileContents) : "File contents do match";

        //Create pull request and get pull request Id
        Integer prId = GithubPullRequestAPI.createPullRequest("main", "feature-branch", "merge", "merging", staticRepository.AUTHTOKEN,staticRepository.USERNAME,staticRepository.REPONAME);

        //Merge PR from main to feature
        GithubMergeRequestAPI.mergePullRequest(prId, "Get that CSV in here", staticRepository.AUTHTOKEN,staticRepository.USERNAME,staticRepository.REPONAME);

        //Get file from feature and main branch and assert that they are the same
        featureBranchFileContents = Converters.convertBase64ToCSV(GithubGetAPI.getFile(staticRepository.USERNAME, staticRepository.AUTHTOKEN, staticRepository.REPONAME, "Companies.csv", "feature-branch"));
        mainBranchFileContents = Converters.convertBase64ToCSV(GithubGetAPI.getFile(staticRepository.USERNAME, staticRepository.AUTHTOKEN, staticRepository.REPONAME, "Companies.csv", "main"));

        //assert that the two strings from the CSV files in main and feature are the same after merging the branches
        assert featureBranchFileContents.equals(mainBranchFileContents) : "File contents do not match";
    }
}