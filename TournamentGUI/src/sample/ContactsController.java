package sample;

import com.lyzstudios.people.Person;
import com.lyzstudios.tournamentapp.Rank;
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
 * Created by Joshua on 5/1/2017.
 */
public class ContactsController {

	private List<Person> contacts; // list of contacts

	private FilteredList<Person> filteredList; // a list used to filter people for the contact list

	private Predicate<Person> wantAllItems;// used to filter for all people in contact list

	@FXML
	private ListView<Person> contactsListView;

	@FXML
	private TextField contactFName;
	@FXML
	private TextField contactMName;
	@FXML
	private TextField contactLName;
	@FXML
	private TextField monthField;
	@FXML
	private TextField dayField;
	@FXML
	private TextField yearField;
	@FXML
	private TextField weightField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private ComboBox stateComboBox;
	@FXML
	private TextField zipField;
	@FXML
	private ComboBox rankComboBox;
	@FXML
	private RadioButton maleToggle;
	@FXML
	private RadioButton femaleToggle;
	@FXML
	private ToggleGroup genderGroup;
	@FXML
	private TextField searchTextField;


	@FXML
	private BorderPane mainBorderPane; // used to get a reference to the app window

	@FXML
	public void initialize(){


		contactsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				try {
					Person person = contactsListView.getSelectionModel().getSelectedItem();
					contactFName.setText(person.getFirstName());
					contactMName.setText(person.getMiddleName());
					contactLName.setText(person.getLastName());
					monthField.setText(Integer.toString(person.getBirthdate().getMonthValue()));
					dayField.setText(Integer.toString(person.getBirthdate().getDayOfMonth()));
					yearField.setText(Integer.toString(person.getBirthdate().getYear()));
					weightField.setText(Float.toString(person.getWeightInPounds()));
					heightField.setText(Float.toString(person.getHeightInInches()));
					emailField.setText(person.getEmail());
					phoneField.setText(person.getPhoneNumber());
					streetField.setText(person.getStreet());
					cityField.setText(person.getCity());
					stateComboBox.setValue(person.getState());
					zipField.setText(person.getZip());
					rankComboBox.setValue(person.getRank());
					if (person.getGender() == "Male") {
						genderGroup.selectToggle(maleToggle);
					} else {
						genderGroup.selectToggle(femaleToggle);
					}
				} catch(NullPointerException e) {
					System.out.println("hiccup");
				}

			}
		});
		handleListView();

		rankComboBox.getItems().setAll(Rank.values());

	}

	private void handleListView(){
		contacts = (List) TournamentAppData.getInstance().getContacts();

		wantAllItems = new Predicate<Person>() {
			@Override
			public boolean test(Person item) {
				return true;
			}
		};

		// filter the items in the list
		filteredList = new FilteredList<Person>(TournamentAppData.getInstance().getContacts(), wantAllItems);

		// change todoItems observable list to a sorted list for sorting
		SortedList<Person> sortedList = new SortedList<Person>(filteredList,
				new Comparator<Person>() {
					@Override
					public int compare(Person p1, Person p2) {
						return p1.getLastName().compareTo(p2.getLastName());
					}
				});

		contactsListView.setItems(sortedList);

		contactsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		contactsListView.getSelectionModel().selectFirst();
	}

	@FXML
	public void showNewContactDialog() {
		// create a new instance of the dialog window
		Dialog<ButtonType> dialog = new Dialog<>();
		// set the owner of the new dialog to the window of the mainwindow.fxml
		dialog.initOwner(mainBorderPane.getScene().getWindow());
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
			contactsListView.getSelectionModel().select(person);
		}
	}
	
	@FXML
	public void editContact(){
		String fName = contactFName.getText().trim();
		String lName = contactLName.getText().trim();
		String mName = contactMName.getText().trim();
		int month = Integer.parseInt(monthField.getText());
		int day = Integer.parseInt(dayField.getText());
		int year = Integer.parseInt(yearField.getText());
		float weight = 0;
		if(!weightField.getText().isEmpty() ){
			weight = Float.parseFloat(weightField.getText());
		}
		float height = 0;
		if(!heightField.getText().isEmpty()){
			height = Float.parseFloat(heightField.getText());
		}
		String email = emailField.getText();
		String phone = phoneField.getText();
		String street = streetField.getText();
		String city = cityField.getText();
		String state = (String) stateComboBox.getSelectionModel().getSelectedItem();
		String zip = zipField.getText();

		String gender = "";

		if(genderGroup.getSelectedToggle() == maleToggle){
			gender = "Male";
		} else {
			gender = "Female";
		}

		Rank rank = (Rank) rankComboBox.getSelectionModel().getSelectedItem();


		Person editedPerson  = null;
		for (Person person :  TournamentAppData.getInstance().getContacts()) {
			if(person.equals(contactsListView.getSelectionModel().getSelectedItem())){
				editedPerson = person;
			}
		}
		


		// create confirmation dialog
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm Contact Edit");
		alert.setHeaderText("Edit contact: "+editedPerson.getFullName());
		alert.setContentText("Are you sure you would like to edit this contact? Press OK to confirm or CANCEL to back out.");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK){
			editedPerson.setFirstName(fName);
			editedPerson.setMiddleName(mName);
			editedPerson.setLastName(lName);
			editedPerson.setBirthdate(year,month,day);
			editedPerson.setWeightInPounds(weight);
			editedPerson.setHeightInInches(height);
			editedPerson.setEmail(email);
			editedPerson.setPhoneNumber(phone);
			editedPerson.setState(state);
			editedPerson.setStreet(street);
			editedPerson.setCity(city);
			editedPerson.setZip(zip);
			editedPerson.setGender(gender);
			editedPerson.setRank(rank);
		}

		handleListView();

	}

	@FXML
	public void searchByLastName(){

		String search = searchTextField.getText().trim().toLowerCase();
		for(Person person:contactsListView.getItems()){
			if(person.getLastName().toLowerCase().contains(search)){
				contactsListView.getSelectionModel().select(person);
				return;
			}
		}
	}

	@FXML
	public void goHome(){
		try{
			Stage stage;
			stage = (Stage) mainBorderPane.getScene().getWindow();
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

	@FXML
	public void handleTournamentsViewLoad(){


		try{
			Stage stage;
			stage = (Stage) contactsListView.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("tournamentwindow.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e){
			e.printStackTrace();
		}
	}


	// TODO add ability to change combo box color based on belt color
}
