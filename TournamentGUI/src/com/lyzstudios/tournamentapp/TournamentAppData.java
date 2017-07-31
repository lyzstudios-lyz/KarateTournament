package com.lyzstudios.tournamentapp;

import com.lyzstudios.people.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 5/1/2017.
 */
public class TournamentAppData {
	private static TournamentAppData instance = new TournamentAppData();

	private ObservableList<Tournament> tournaments = FXCollections.observableArrayList();
	private ObservableList<Person> contacts = FXCollections.observableArrayList();

	private ObservableList<Competitor> competitors = FXCollections.observableArrayList();

	public ObservableList<Competitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(ObservableList<Competitor> competitors) {
		this.competitors = competitors;
	}

	private Tournament currentTournament;

	public Tournament getCurrentTournament() {

		return currentTournament;
	}

	public void setCurrentTournament(Tournament currentTournament) {

		for(Tournament tournament : tournaments){
			if(tournament.equals(currentTournament)){
				this.currentTournament = tournament;
				return;
			}
		}
		this.currentTournament = null;
	}

	public static TournamentAppData getInstance(){
		return instance;
	}

	public boolean addContact(Person contact){
		return contacts.add(contact);
	}

	public ObservableList<Person> getContacts(){
		return contacts;
	}

	public boolean addTournament(Tournament tournament){
		return tournaments.add(tournament);
	}

	public ObservableList<Tournament> getTournamnets(){
		return tournaments;
	}



}
