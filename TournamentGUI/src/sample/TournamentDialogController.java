package sample;

import com.lyzstudios.tournamentapp.Tournament;
import com.lyzstudios.tournamentapp.TournamentAppData;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;


/**
 * Copyright LyzStudios 2017.
 * Created by Joshua on 5/5/2017.
 */
public class TournamentDialogController {
	@FXML
	private TextField hostField;
	@FXML
	private DatePicker dateField;

	public Tournament processResults(){
		String host = hostField.getText().trim();
		LocalDate date = dateField.getValue();

		Tournament tournament = new Tournament(date,host);

		TournamentAppData.getInstance().addTournament(tournament);

		return tournament;
	}
}
