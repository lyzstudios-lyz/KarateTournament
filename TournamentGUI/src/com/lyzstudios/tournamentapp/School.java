package com.lyzstudios.tournamentapp;

import com.lyzstudios.people.Person;

/**
 * Created by Joshua on 4/9/2017.
 */
public class School {
    private String name, street, city, state, country, zip, phoneNumber;
    private Person instructor;

    public School(){
        this.name = "";
        this.street = "";
        this.city = "";
        this.state = "";
        this.country = "";
        this.zip = "";
        this.phoneNumber = "";
        this.instructor = null;
    }

    public School(String name, String street, String city, String state, String country, String zip, String phoneNumber, Person instructor) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.instructor = instructor;
    }

    public School(String name, String city, String state, Person instructor) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.instructor = instructor;
    }

    public School(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = "";
        this.zip = "";
        this.phoneNumber = "";
        this.instructor = null;
        this.street = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        name = name.toLowerCase();
        String[] words = name.split(" ");
        name = "";

        for(String word: words){
            if(word == words[words.length -1]){
                word = word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase();
                name += word;
            } else {
                word = word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase();
                name += word + " ";
            }
        }
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String areaCode = "("+phoneNumber.substring(0,3)+")";
        String number = phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6);
        phoneNumber = areaCode + number;
        this.phoneNumber = phoneNumber;
    }

    public Person getInstructor() {
        return instructor;
    }

    public void setInstructor(Person instructor) {
        this.instructor = instructor;
    }

    public String getFormattedAddress(){
        return street+"\n"+city+", "+state+" "+zip;
    }

    public void printInfoToConsole(){
        System.out.println("School Name: "+name);
        if(instructor != null){
            System.out.println("Instructor: "+instructor.getFullName());

        }else {
            System.out.println("Instructor: ");
        }
        System.out.println(getFormattedAddress());
        System.out.println("Phone Number: "+phoneNumber);
        System.out.println("***************************************************");
        System.out.println();
    }
}
