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
    public void setName(CompanyNames companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }
    public void setNumberOfCustomers( int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public Countries getCountry() {
        return country;
    }
    public void setCountry(Countries country) {
        this.country = country;
    }
}