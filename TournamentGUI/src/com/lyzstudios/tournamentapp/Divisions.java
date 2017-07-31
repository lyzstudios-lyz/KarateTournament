package com.lyzstudios.tournamentapp;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 4/13/2017.
 */
public class Divisions {
    private Set<Competitor> competitors = new HashSet<>();
    private String name;
    private DivisionType divType;
    private Set<Rank> ranks = new HashSet<>();
    private int minAge;
    private int maxAge;

    public Divisions(DivisionType divType, Set<Rank> ranks, int minAge, int maxAge){
        this.divType = divType;
        this.ranks = ranks;
        this.minAge = minAge;
        this.maxAge = maxAge;
        setName();
    }

    private void setName() {
        String holder = "";
        holder += this.divType+": ";
        int i =0;
        for(Rank r: this.ranks){
            if(i == ranks.size()-1){
                holder+=r;
            }else {
                holder+=r+"_";
            }
            i++;
        }

        holder+=": "+this.minAge+"-"+this.maxAge;
        this.name = holder;
    }

    public void setDivType(DivisionType divType) {
        this.divType = divType;
    }

    public void setRanks(Set<Rank> ranks) {
        this.ranks = ranks;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Set<Competitor> getCompetitors() {
        return competitors;
    }

    public String getName() {
        return name;
    }

    public DivisionType getDivType() {
        return divType;
    }

    public Set<Rank> getRanks() {
        return ranks;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public enum DivisionType{
        FORMS, WEAPONS, SPARRING
    }
}
