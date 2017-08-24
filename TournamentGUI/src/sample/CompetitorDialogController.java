package sample;

import com.lyzstudios.people.Person;
import com.lyzstudios.tournamentapp.Competitor;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import sun.security.ssl.Debug;

/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 5/28/2017.
 */
public class CompetitorDialogController {

	@FXML
	private ComboBox<Person> contactsDropDown;

	@FXML
	private CheckBox emptyCheckBox;
	@FXML
	private CheckBox weaponsCheckBox;
	@FXML
	private CheckBox sparringCheckBox;



	@FXML
	public void initialize(){


		ObservableList<Person> contacts = TournamentAppData.getInstance().getContacts();
		for(Person person : contacts){
			System.out.println(person.getFullName());
		}
		contactsDropDown.setItems(contacts);
	}


	public Competitor processResults(){


		//Get the selected person
		Person person = contactsDropDown.getSelectionModel().getSelectedItem();


		Competitor newCompetitor = new Competitor();

		//convert the person to a competitor
		newCompetitor.setEqualToPerson(person);

		if(emptyCheckBox.isSelected()){
			newCompetitor.setInForms(true);
		}
		if(weaponsCheckBox.isSelected()){
			newCompetitor.setInWeapons(true);
		}
		if(sparringCheckBox.isSelected()){
			newCompetitor.setInSparring(true);
		}

		TournamentAppData.getInstance().getCurrentTournament().addCompetitor(newCompetitor);
		for(Competitor comp: TournamentAppData.getInstance().getCurrentTournament().getCompetitors()){
			System.out.println(comp);
		}

		return newCompetitor;
	}
}
