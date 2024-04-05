package modelClasses;

import enumRepo.*;

public class Company {
    private CompanyNames companyName;
    private int numberOfEmployees;
    private int numberOfCustomers;
    private Countries country;

    // Constructor
    public Company(CompanyNames name, int numberOfEmployees, int numberOfCustomers, Countries country) {
        this.companyName = name;
        this.numberOfEmployees = numberOfEmployees;
        this.numberOfCustomers = numberOfCustomers;
        this.country = country;
    }

    // Getters and Setters
    public CompanyNames getName() {
        return companyName;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public Countries getCountry() {
        return country;
    }
}