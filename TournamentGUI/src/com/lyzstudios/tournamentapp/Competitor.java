package com.lyzstudios.tournamentapp;

import com.lyzstudios.people.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 4/13/2017.
 */
public class Competitor extends Person {
    private boolean inForms =false;
    private boolean inWeapons = false;
    private boolean inSparring = false;
    private float formsScore = 0;
    private float weaponsScore = 0;
    private int formsPlace = 0;
    private int weaponsPlace = 0;
    private int sparringPlace = 0;
    private int gcScore = 0;

    public Competitor(){

    }
    public Competitor(boolean inForms, boolean inWeapons, boolean inSparring) {
        super();
        this.inForms = inForms;
        this.inWeapons = inWeapons;
        this.inSparring = inSparring;
    }


    public void setEqualToPerson(Person person){

        this.setFirstName(person.getFirstName());
        this.setMiddleName(person.getMiddleName());
        this.setLastName(person.getLastName());
        this.setGender(person.getGender());
        this.setBirthdate(person.getBirthdate().getYear(), person.getBirthdate().getMonthValue(), person.getBirthdate().getDayOfMonth());
        this.setWeightInPounds(person.getWeightInPounds());
        this.setHeightInInches(person.getHeightInInches());
        this.setEmail(person.getEmail());
        this.setPhoneNumber(person.getPhoneNumber());
        this.setStreet(person.getStreet());
        this.setCity(person.getCity());
        this.setState(person.getState());
        this.setCountry(person.getCountry());
        this.setZip(person.getZip());
        this.setRank(person.getRank());
        this.setSchool(person.getSchool());

    }

    public int getGcScore(){
        return calcGCScore();
    }

    public boolean isInForms() {
        return inForms;
    }

    public boolean isInWeapons() {
        return inWeapons;
    }

    public boolean isInSparring() {
        return inSparring;
    }

    public float getFormsScore() {
        return formsScore;
    }

    public float getWeaponsScore() {
        return weaponsScore;
    }

    public int getFormsPlace() {
        return formsPlace;
    }

    public int getWeaponsPlace() {
        return weaponsPlace;
    }

    public int getSparringPlace() {
        return sparringPlace;
    }

    public void setInForms(boolean inForms) {
        this.inForms = inForms;
    }

    public void setInWeapons(boolean inWeapons) {
        this.inWeapons = inWeapons;
    }

    public void setInSparring(boolean inSparring) {
        this.inSparring = inSparring;
    }

    public void setFormsScore(float formsScore) {
        this.formsScore = formsScore;
    }

    public void setWeaponsScore(float weaponsScore) {
        this.weaponsScore = weaponsScore;
    }

    public void setFormsPlace(int formsPlace) {
        this.formsPlace = formsPlace;
    }

    public void setWeaponsPlace(int weaponsPlace) {
        this.weaponsPlace = weaponsPlace;
    }

    public void setFormsPlace(int formsPlace, float score) {
        this.formsPlace = formsPlace;
        this.formsScore = score;
    }

    public void setWeaponsPlace(int weaponsPlace, float score) {
        this.weaponsPlace = weaponsPlace;
        this.weaponsScore = score;
    }

    public void setSparringPlace(int sparringPlace) {
        this.sparringPlace = sparringPlace;
    }

    public int calcGCScore(){
        Set<Integer> places = new HashSet<>();
        if(inForms){
            places.add(formsPlace);
        }
        if(inWeapons){
            places.add(weaponsPlace);
        }
        if(inSparring){
            places.add(sparringPlace);

        }
        int score= 0;

        if(!(places.isEmpty())){
            for(Integer i: places){
                if(i <= 4) {
                    switch (i) {
                        case 1:
                            score += 4;
                            break;
                        case 2:
                            score += 3;
                            break;
                        case 3:
                            score += 2;
                            break;
                        case 4:
                            score += 1;
                            break;

                    }
                }
            }
        }

        return score;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj instanceof Competitor) {
            Competitor comp = (Competitor) obj;
            if (this.getFirstName().equals(comp.getFirstName())  &&
                    this.getLastName().equals(comp.getLastName())) {
                return (this.getMiddleName() == comp.getMiddleName());
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return  super.getLastName()+", "+super.getFirstName(); //getFullName();
    }
}
