package sample;

import com.lyzstudios.people.Person;
import com.lyzstudios.tournamentapp.Tournament;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {


	private List<Tournament> tournaments; // list of contacts

	private FilteredList<Tournament> filteredTournamentList; // a list used to filter people for the contact list

	private Predicate<Tournament> wantAllTournaments;// used to filter for all people in contact list


	@FXML
	private BorderPane mainPane;

	@FXML
	private Button viewContactsBTN;


	@FXML
	private ListView<Tournament> tournamentListView;

	@FXML
	public void initialize() {


		tournaments = (List) TournamentAppData.getInstance().getTournamnets();

		tournamentListView.getItems().addAll(tournaments);

		wantAllTournaments = new Predicate<Tournament>() {
			@Override
			public boolean test(Tournament item) {
				return true;
			}
		};

		// filter the items in the list
		filteredTournamentList = new FilteredList<Tournament>(TournamentAppData.getInstance().getTournamnets(), wantAllTournaments);

		// change todoItems observable list to a sorted list for sorting
		SortedList<Tournament> sortedList = new SortedList<Tournament>(filteredTournamentList,
				new Comparator<Tournament>() {
					@Override
					public int compare(Tournament p1, Tournament p2) {
						return p1.getDate().compareTo(p2.getDate());
					}
				});

		tournamentListView.setItems(sortedList);

		tournamentListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		tournamentListView.getSelectionModel().selectFirst();

		TournamentAppData.getInstance().setCurrentTournament(tournamentListView.getSelectionModel().getSelectedItem());

	}

	@FXML
	public void handleContactsViewLoad(){
		try{
			Stage stage;
			stage = (Stage) viewContactsBTN.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("contactwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	public void handleTournamentsViewLoad(){

		TournamentAppData.getInstance().setCurrentTournament(tournamentListView.getSelectionModel().getSelectedItem());

		try{
			Stage stage;
			stage = (Stage) viewContactsBTN.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("tournamentwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}


	@FXML
	public void showNewContactDialog() {
		// create a new instance of the dialog window
		Dialog<ButtonType> dialog = new Dialog<>();
		// set the owner of the new dialog to the window of the mainwindow.fxml
		dialog.initOwner(mainPane.getScene().getWindow());
		// add a title to the window
		dialog.setTitle("Add new Contact");
		// set the header text
		dialog.setHeaderText("Insert a contact and add its details. Press 'Ok' when complete. Press 'Cancel' to cancel.");
		// load the dialogController.fxml
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));
		try {
			// set the content of the local dialog pane to match the todoItemDialog.fxml
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}

		// add the buttons to the dialog
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			ContactDialogController controller = fxmlLoader.getController();
			Person person = controller.processResults();
			handleContactsViewLoad();
		}
	}

	@FXML
	public void showNewTournamentDialog() {
		// create a new instance of the dialog window
		Dialog<ButtonType> dialog = new Dialog<>();
		// set the owner of the new dialog to the window of the mainwindow.fxml
		dialog.initOwner(mainPane.getScene().getWindow());
		// add a title to the window
		dialog.setTitle("Add new Tournament");
		// set the header text
		dialog.setHeaderText("Add the host's name and the date of the tournament. Press 'Ok' when complete. Press 'Cancel' to cancel.");
		// load the dialogController.fxml
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("tournamentDialog.fxml"));
		try {
			// set the content of the local dialog pane to match the todoItemDialog.fxml
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}

		// add the buttons to the dialog
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			TournamentDialogController controller = fxmlLoader.getController();
			Tournament tournament = controller.processResults();
			tournamentListView.getSelectionModel().select(tournament);
		}
	}


	@FXML
	public void handleExit(){
		Platform.exit();
	}
}
