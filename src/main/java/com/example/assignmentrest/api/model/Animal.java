package com.example.assignmentrest.api.model;


public class Animal {
    private String date;
    private double weight;
    private double registrationNr;
    private String origin;

    public Animal(String date, double weight, double registrationNr, String origin) {
        this.date = date;
        this.weight = weight;
        this.registrationNr = registrationNr;
        this.origin = origin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(double registrationNr) {
        this.registrationNr = registrationNr;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
