package sample;

import com.lyzstudios.tournamentapp.Competitor;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


/**
  * Copyright LyzStudios 2017.
  * Created by Joshua on 5/7/2017.
  *
  */


public class TournamentController {

	@FXML
	private BorderPane mainPane;

	@FXML
	private Label tournamentNameLabel;

	@FXML
	private ListView<Competitor> competitorsListView;

	@FXML
	private Label compName;

	private List<Competitor> competitors;
	private FilteredList<Competitor> filteredList; // a list used to filter people for the contact list
	private Predicate<Competitor> wantAllItems;// used to filter for all people in contact list

	@FXML
	private CheckBox emptyBox;
	@FXML
	private CheckBox weaponsBox;
	@FXML
	private CheckBox sparringBox;

	@FXML
	private TextField emptyScore;
	@FXML
	private  TextField emptyPlace;
	@FXML
	private TextField WeaponsScore;
	@FXML
	private TextField WeaponsPlace;
	@FXML
	private TextField SparringPlace;
	@FXML
	private  Label gcScoreLabel;



	@FXML
	public void initialize(){

		tournamentNameLabel.setText(TournamentAppData.getInstance().getCurrentTournament().toString());

		competitorsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Competitor>() {
			@Override
			public void changed(ObservableValue<? extends Competitor> observable, Competitor oldValue, Competitor newValue) {
				try{
					Competitor competitor = competitorsListView.getSelectionModel().getSelectedItem();
					compName.setText(competitor.getFirstName()+" "+competitor.getLastName());
					emptyBox.setSelected(competitor.isInForms());
					weaponsBox.setSelected(competitor.isInWeapons());
					sparringBox.setSelected(competitor.isInSparring());

					emptyScore.setText(String.valueOf(competitor.getFormsScore()));
					emptyPlace.setText(String.valueOf(competitor.getFormsPlace()));
					WeaponsScore.setText(String.valueOf(competitor.getWeaponsScore()));
					WeaponsPlace.setText(String.valueOf(competitor.getWeaponsPlace()));
					SparringPlace.setText(String.valueOf(competitor.getSparringPlace()));
					gcScoreLabel.setText(String.valueOf(competitor.getGcScore())+"gcp");
				} catch(NullPointerException e) {
					System.out.println("hiccup");
				}
			}
		});

		handleListView();
	}


	@FXML
	public void editCompetitor(){

		boolean emptyHands = emptyBox.isSelected();
		boolean weapons = weaponsBox.isSelected();
		boolean sparring = sparringBox.isSelected();
		float emptyScoreF = Float.valueOf(emptyScore.getText());
		float weaponsScoreF = Float.valueOf(WeaponsScore.getText());
		int emptyPlaceI = Integer.valueOf(emptyPlace.getText());
		int weaponsPlaceI = Integer.valueOf(WeaponsPlace.getText());
		int sparringPlaceI = Integer.valueOf(SparringPlace.getText());



		Competitor editedPerson  = null;
		for (Competitor competitor :  TournamentAppData.getInstance().getCurrentTournament().getCompetitors()) {
			if(competitor.equals(competitorsListView.getSelectionModel().getSelectedItem())){
				editedPerson = competitor;
				break;
			}
		}



		// create confirmation dialog
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Competitor Edit");
		alert.setHeaderText("Edit Competitor: "+editedPerson.getFullName());
		alert.setContentText("Are you sure you would like to edit this competitor? Press OK to confirm or CANCEL to back out.");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK){
			if(editedPerson != null){
				editedPerson.setInForms(emptyHands);
				editedPerson.setInWeapons(weapons);
				editedPerson.setInSparring(sparring);
				editedPerson.setFormsPlace(emptyPlaceI, emptyScoreF);
				editedPerson.setWeaponsPlace(weaponsPlaceI, weaponsScoreF);
				editedPerson.setSparringPlace(sparringPlaceI);
				editedPerson.getGcScore();

			}

		}

		handleListView();

	}


	@FXML
	public void handleContactsViewLoad(){
		try{
			Stage stage;
			stage = (Stage) mainPane.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("contactwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	public void handleNewCompetitorViewLoad(){
		// create a new instance of the dialog window
		Dialog<ButtonType> dialog = new Dialog<>();
		// set the owner of the new dialog to the window of the mainwindow.fxml
		dialog.initOwner(mainPane.getScene().getWindow());
		// add a title to the window
		dialog.setTitle("Add new Competitor");
		// set the header text
		dialog.setHeaderText("Select a competitor from the contact list. Click all boxes that the competitor will compete in.");
		// load the dialogController.fxml
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("competitorDialog.fxml"));
		try {
			// set the content of the local dialog pane to match the todoItemDialog.fxml
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Couldn't load the competitor dialog");
			e.printStackTrace();
			return;
		}

		// add the buttons to the dialog
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			CompetitorDialogController controller = fxmlLoader.getController();
			Competitor person = controller.processResults();
			competitorsListView.getSelectionModel().select(person);
		}
	}

	private void handleListView(){
		competitors = (List) TournamentAppData.getInstance().getCurrentTournament().getCompetitors();

		wantAllItems = new Predicate<Competitor>() {
			@Override
			public boolean test(Competitor item) {
				return true;
			}
		};

		// filter the items in the list
		filteredList = new FilteredList<Competitor>(TournamentAppData.getInstance().getCurrentTournament().getCompetitors(), wantAllItems);

		// change todoItems observable list to a sorted list for sorting
		SortedList<Competitor> sortedList = new SortedList<Competitor>(filteredList,
				new Comparator<Competitor>() {
					@Override
					public int compare(Competitor p1, Competitor p2) {
						return p1.getLastName().compareTo(p2.getLastName());
					}
				});

		competitorsListView.setItems(sortedList);

		competitorsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		competitorsListView.getSelectionModel().selectFirst();
	}


	@FXML
	public void handleTournamentsViewLoad(){

		try{
			Stage stage;
			stage = (Stage) mainPane.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("tournamentwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	public void goHome(){
		try{
			Stage stage;
			stage = (Stage) mainPane.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}

	@FXML
	public void handleExit(){
		Platform.exit();
	}

}
