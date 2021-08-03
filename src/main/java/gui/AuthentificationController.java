package gui;

import java.io.IOException;

import dto.AuthentificationDTO;
import gui.util.HttpRequestUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class AuthentificationController {

	public static String USER_API = Main.backURL + "/users";

	private Main mainApp;

	@FXML
	private TextField entrepriseTxt;

	@FXML
	private TextField codeAccee;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * verifier si l'utilisateur exist ou pas S'il existe il va t'étre rediriger
	 * vers l'écran principale de l'application
	 */
	public void goToDashboard() {
		AuthentificationDTO auth = new AuthentificationDTO();
		auth.setName(entrepriseTxt.getText());
		auth.setPassword(codeAccee.getText());
		Boolean allow = Boolean.FALSE;
		try {
			String test = HttpRequestUtils.post(USER_API, mainApp.gson.toJson(auth));
			allow = Boolean.valueOf(test);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Probléme");
			alert.setHeaderText("Probléme de connexion!");
			alert.showAndWait();
		}
		if (allow) {
			mainApp.dashboardWindow();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alerte");
			alert.setHeaderText("Vérifier le nom de l'entrepise et le code d'accées");
			alert.showAndWait();
		}
	}

	public void goToCreateAccount() {
		mainApp.createAccountWindow();
	}

}
