package sample;

import com.lyzstudios.people.Person;
import com.lyzstudios.tournamentapp.Rank;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 5/1/2017.
 */
public class ContactDialogController {


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
	private Label nameLabel;

	@FXML
	private Label fNameLabel;

	@FXML
	private Label lNameLabel;

	public void initialize(){
		rankComboBox.getItems().setAll(Rank.values());
		rankComboBox.getSelectionModel().selectFirst();
		genderGroup.selectToggle(maleToggle);
	}

	public Person processResults(){

		String fName = contactFName.getText().trim();
		String lName = contactLName.getText().trim();
		String mName = contactMName.getText().trim();
		int month;
		int day;
		int year;

		if (monthField.getText().trim().isEmpty()) {
			month = 1;
		} else {
			month = Integer.parseInt(monthField.getText());

		}

		if (dayField.getText().trim().isEmpty()) {
			day = 1;
		} else {
			day = Integer.parseInt(dayField.getText());

		}

		if (yearField.getText().trim().isEmpty()) {
			year = 1;
		} else {
			year = Integer.parseInt(yearField.getText());

		}

		float weight = 0;
		if (!weightField.getText().isEmpty()) {
			weight = Float.parseFloat(weightField.getText());
		}
		float height = 0;
		if (!heightField.getText().isEmpty()) {
			height = Float.parseFloat(heightField.getText());
		}
		String email = emailField.getText();
		String phone = phoneField.getText();
		String street = streetField.getText();
		String city = cityField.getText();
		String state = (String) stateComboBox.getSelectionModel().getSelectedItem();
		String zip = zipField.getText();

		String gender = "";

		if (genderGroup.getSelectedToggle() == maleToggle) {
			gender = "Male";
		} else {
			gender = "Female";
		}

		Rank rank = (Rank) rankComboBox.getSelectionModel().getSelectedItem();

		Person newPerson = new Person();

		// TODO check for name at minimum
		// TODO error check for birthdate when passing values
		newPerson.setFirstName(fName);
		newPerson.setMiddleName(mName);
		newPerson.setLastName(lName);
		newPerson.setBirthdate(year, month, day);
		newPerson.setWeightInPounds(weight);
		newPerson.setHeightInInches(height);
		newPerson.setEmail(email);
		newPerson.setPhoneNumber(phone);
		newPerson.setState(state);
		newPerson.setStreet(street);
		newPerson.setCity(city);
		newPerson.setZip(zip);
		newPerson.setGender(gender);
		newPerson.setRank(rank);

		TournamentAppData.getInstance().addContact(newPerson);

		return newPerson;
	}

	@FXML
	public void ErrorCheck(){
		if (!(contactFName.getText().trim().isEmpty()) && !(contactLName.getText().trim().isEmpty())){
			nameLabel.setTextFill(Color.BLACK);
		} else {
			nameLabel.setTextFill(Color.RED);
		}

		if(!(contactFName.getText().trim().isEmpty())){
			fNameLabel.setTextFill(Color.BLACK);
		} else {
			fNameLabel.setTextFill(Color.RED);
		}

		if(!(contactLName.getText().trim().isEmpty())){
			lNameLabel.setTextFill(Color.BLACK);
		} else{
			lNameLabel.setTextFill(Color.RED);
		}
	}

}
