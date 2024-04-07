package utils;

import modelClasses.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import enumRepo.*;

public class GenerateRecords {
    // Method to generate a list of n Companies
    public static List<Company> generateCompanies(int n) {
        List<Company> companies = new ArrayList<>();
        Random random = new Random();
        Countries[] countries = Countries.values(); // Get all enum values
        CompanyNames[] companyNames = CompanyNames.values(); // Get all enum values

        for (int i = 0; i < n; i++) {
            CompanyNames companyName = companyNames[random.nextInt(companyNames.length)];// Randomly select a company
            int employees = 50 + random.nextInt(450); // Random number between 50 and 500
            int customers = 100 + random.nextInt(900); // Random number between 100 and 1000
            Countries country = countries[random.nextInt(countries.length)]; // Randomly select a country
            //add the newly created company in the list
            companies.add(new Company(companyName, employees, customers, country));
        }

        return companies;
    }
}