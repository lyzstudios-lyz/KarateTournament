package com.lyzstudios.people;

import com.lyzstudios.tournamentapp.Rank;
import com.lyzstudios.tournamentapp.School;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Joshua on 4/9/2017.
 */
public class Person {
    private String  firstName, middleName = "", lastName, gender;
    private LocalDate birthdate;
    private int     age;
    private float   weight, height;
    private String email, phoneNumber;
    private String street, city, state, country, zip;
    private Rank rank;
    private School school = null;

    public Person(){
        this.firstName = "";
        this.lastName = "";
        birthdate = LocalDate.now();
        gender = "Unknown";
        updateAge();
        weight = 0;
        height = 0;
        email = "";
        phoneNumber = "";
        street = "";
        city = "";
        state ="";
        country ="";
        zip = "";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        birthdate = LocalDate.now();
        gender = "Unknown";
        updateAge();
        weight = 0;
        height = 0;
        email = "";
        phoneNumber = "";
        street = "";
        city = "";
        state ="";
        country ="";
        zip = "";
    }

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        birthdate = LocalDate.now();
        gender = "Unknown";
        updateAge();
        weight = 0;
        height = 0;
        email = "";
        phoneNumber = "";
        street = "";
        city = "";
        state ="";
        country ="";
        zip = "";
    }

    public Person(String firstName, String lastName, String gender, int bYear, int bMonth, int bDay, float weight, float height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        setBirthdate(bYear,bMonth,bDay);
        this.weight = weight;
        this.height = height;
        updateAge();
        email = "";
        phoneNumber = "";
        street = "";
        city = "";
        state ="";
        country ="";
        zip = "";
    }

    public Person(String firstName, String middleName, String lastName, String gender, int bYear, int bMonth, int bDay,
                  float weight, float height) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        setBirthdate(bYear,bMonth,bDay);
        this.weight = weight;
        this.height = height;
        updateAge();
        email = "";
        phoneNumber = "";
        street = "";
        city = "";
        state ="";
        country ="";
        zip = "";
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName.toLowerCase();
        firstName = firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if(!(middleName.isEmpty())){
            middleName = middleName.toLowerCase();
            middleName = middleName.substring(0,1).toUpperCase()+middleName.substring(1).toLowerCase();
        }
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName.toLowerCase();
        lastName = lastName.substring(0,1).toUpperCase()+lastName.substring(1).toLowerCase();
        this.lastName = lastName;
    }

    public String getFullName(){
        if(middleName.equals("")){
            return firstName+" "+lastName;
        } else {
            return firstName+" "+middleName+" "+lastName;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int year, int month, int day){
        this.birthdate = LocalDate.of(year, month, day);
    }

    public int getAge() {
        updateAge();
        return age;
    }

    public void updateAge() {
        LocalDate today = LocalDate.now();

        Period p = Period.between(birthdate, today);

        age = p.getYears();
    }

    public float getWeightInPounds() {
        return weight;
    }

    public void setWeightInPounds(float weight) {
        this.weight = weight;
    }

    public float getHeightInInches() {
        return height;
    }

    public String getHeightInFeet(){
        int feet = (int)getHeightInInches() / 12;
        int inches = (int) getHeightInInches() % 12;
        return (feet+"'"+inches+'"');
    }

    public void setHeightInInches(float height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.substring(0).contains("(") || phoneNumber.isEmpty() ){
            // do nothing if the number has been set or if there is no number
        }else {
            String areaCode = "("+phoneNumber.substring(0,3)+")";
            String number = phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6);
            phoneNumber = areaCode + number;
        }
        this.phoneNumber = phoneNumber;
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

    public void setFullAddress(String street, String city, String state, String zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    public String getFormattedAddress(){
        if(street.equals("") ||
                city.equals("") ||
                state.equals("") ||
                zip.equals("")){
            return "";
        }else {
            return street+"\n"+city+", "+state+" "+zip;
        }
    }

    public void printInfoToConsole(){

        System.out.println("Info on: "+this.getFullName());
        System.out.println("Rank: "+rank);
        System.out.println("Gender: "+this.getGender());
        System.out.println("Age: "+getAge());
        System.out.println("Weight: "+getWeightInPounds()+" lbs");
        System.out.println("Height: "+getHeightInFeet());
        System.out.println("Email: "+this.getEmail());
        System.out.println("Phone Number: "+this.getPhoneNumber());
        System.out.println("Address: \n"+this.getFormattedAddress());
        System.out.println();
        if(this.school != null){
            System.out.println("School: "+school.getName());
            System.out.println("Instructor: "+school.getInstructor().getFullName());
            System.out.println("School Address: \n"+school.getFormattedAddress());
        }

        System.out.println("***************************************************");
        System.out.println();
    }

    @Override
    public int hashCode() {
        return this.getFirstName().hashCode()+57+this.getMiddleName().hashCode()+this.getLastName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj instanceof Person) {
            Person comp = (Person) obj;
            if (this.getFirstName().equals(comp.getFirstName())  &&
                    this.getLastName().equals(comp.getLastName())) {
                return (this.getMiddleName() == comp.getMiddleName());
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return  lastName+", "+firstName; //getFullName();
    }
}
