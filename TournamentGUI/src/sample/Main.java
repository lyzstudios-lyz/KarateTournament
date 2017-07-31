package sample;

import com.lyzstudios.people.Person;
import com.lyzstudios.tournamentapp.Rank;
import com.lyzstudios.tournamentapp.Tournament;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

	    Person person1 = new Person("Joshua", "Sanford");
	    Person person2 = new Person("Tommy", "Stover");
	    Person person3 = new Person("Melissa", "Hinkle");
	    Person person4 = new Person("Melissa", "Hinkle");
	    Person person5 = new Person("Melissa", "Hinkle");
	    Person person6 = new Person("Melissa", "Hinkle");
	    Person person7 = new Person("Melissa", "Hinkle");
	    Person person8 = new Person("Melissa", "Hinkle");
	    Person person9 = new Person("Melissa", "Hinkle");
	    Person person10 = new Person("Melissa", "Hinkle");
	    Person person11 = new Person("Melissa", "Hinkle");
	    Person person12 = new Person("Melissa", "Hinkle");
	    Person person13 = new Person("Melissa", "Hinkle");
	    person1.setBirthdate(1988,06,30);
	    person1.setCity("Delta");
	    person1.setState("CO");
	    person1.setZip("81416");
	    person1.setPhoneNumber("9709648590");
	    person1.setRank(Rank.BROWN);
	    person1.setGender("Male");

	    System.out.println("Person 1 added: "+ TournamentAppData.getInstance().addContact(person1));
	    System.out.println("Person 1 added: "+TournamentAppData.getInstance().addContact(person2));
	    System.out.println("Person 1 added: "+TournamentAppData.getInstance().addContact(person3));
	    TournamentAppData.getInstance().addContact(person4);
	    TournamentAppData.getInstance().addContact(person5);
	    TournamentAppData.getInstance().addContact(person6);
	    TournamentAppData.getInstance().addContact(person7);
	    TournamentAppData.getInstance().addContact(person8);
	    TournamentAppData.getInstance().addContact(person9);
	    TournamentAppData.getInstance().addContact(person10);
	    TournamentAppData.getInstance().addContact(person11);
	    TournamentAppData.getInstance().addContact(person12);
	    TournamentAppData.getInstance().addContact(person13);

	    Tournament tournament = new Tournament(LocalDate.now(), "DKL");
	    System.out.println("Tournament added: "+TournamentAppData.getInstance().addTournament(tournament));


        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("Tournament App - Karate");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
