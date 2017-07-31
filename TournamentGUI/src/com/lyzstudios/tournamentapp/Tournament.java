package com.lyzstudios.tournamentapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 4/13/2017.
 */
public class Tournament {
    private LocalDate date;
    private Set<Divisions> divisions = new HashSet<>();
    private ObservableList<Competitor> competitors = FXCollections.observableArrayList();
    private String host;

    public Tournament(LocalDate date, String host) {
        this.date = date;
        this.host = host;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addCompetitor(Competitor comp){
        competitors.add(comp);
    }

    public ObservableList<Competitor> getCompetitors(){
        return competitors;
    }

    @Override
    public String toString() {
        return host+": "+date;
    }
}
