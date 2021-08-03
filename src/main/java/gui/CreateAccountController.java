package gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gui.util.HttpRequestUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateAccountController {

	private Main mainApp;

	@FXML
	private TextField txtNom;
	@FXML
	private TextField txtAdresse;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtIdentifiant;
	@FXML
	private TextField txtQstSec;
	@FXML
	private TextField txtRepSec;

	@FXML
	private PasswordField txtPasssword1;
	@FXML
	private PasswordField txtPasssword2;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void goToLogin() {
		mainApp.mainWindow();
	}

	/**
	 * Créer un compte pour un nouveau utilisateur
	 */
	public void createAccount() {
		if (txtNom.getText().isEmpty() || txtAdresse.getText().isEmpty() || txtEmail.getText().isEmpty()
				|| txtIdentifiant.getText().isEmpty() || txtQstSec.getText().isEmpty()
				|| txtRepSec.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerte");
			alert.setHeaderText("Veuillez vérifier les champs de formulaire");
			alert.showAndWait();
			return;
		}
		if (txtPasssword1.getText().isEmpty() || txtPasssword2.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Alerte");
			alert.setHeaderText("Veuillez vérifier le mot de passe!");
			alert.showAndWait();
			return;
		}

		final Map<String, Object> account = new HashMap(0);
		account.put("name", txtNom.getText());
		account.put("adresse", txtAdresse.getText());
		account.put("email", txtEmail.getText());
		account.put("motDePasse", txtPasssword1.getText());
		account.put("questionSecrete", txtQstSec.getText());
		account.put("reponseQuestionSecrete", txtRepSec.getText());
		account.put("id", null);
		String body = mainApp.gson.toJson(account);
		try {
			HttpRequestUtils.post(AuthentificationController.USER_API+"/createAccount", body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
