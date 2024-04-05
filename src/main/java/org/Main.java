package org;

import modelClasses.Company;
import utils.GenerateRecords;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Company> companiesList = GenerateRecords.generateCompanies(10); // Generate 10 companies

        for (Company company : companiesList) {
            System.out.println("Name: " + company.getName() +
                    ", Employees: " + company.getNumberOfEmployees() +
                    ", Customers: " + company.getNumberOfCustomers() +
                    ", Country: " + company.getCountry());
        }
    }
}