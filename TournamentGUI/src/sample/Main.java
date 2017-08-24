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

	    InitTestData();


        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("Tournament App - Karate");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();



    }

    private void InitTestData(){
		Person person1 = new Person("Joshua", "Sanford");
		Person person2 = new Person("Tommy", "Stover");
		Person person3 = new Person("Melissa", "Hinkle");
		person1.setBirthdate(1988,06,30);
		person1.setCity("Delta");
		person1.setState("CO");
		person1.setZip("81416");
		person1.setPhoneNumber("9709648590");
		person1.setRank(Rank.BROWN);
		person1.setGender("Male");

		System.out.println("Person 1 added: "+ TournamentAppData.getInstance().addContact(person1));
		System.out.println("Person 2 added: "+TournamentAppData.getInstance().addContact(person2));
		System.out.println("Person 3 added: "+TournamentAppData.getInstance().addContact(person3));

		Tournament tournament = new Tournament(LocalDate.now(), "DKL");
		System.out.println("Tournament added: "+TournamentAppData.getInstance().addTournament(tournament));
	}


    public static void main(String[] args) {
        launch(args);
    }
}
