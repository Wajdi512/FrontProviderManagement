package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/Authentification.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			Scene scene = new Scene(overviewPage);
			stage.setResizable(false);
			stage.setTitle("Fournisseurs");
			AuthentificationController controller = loader.getController();
			controller.setMainApp(this);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dashboardWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/Dashboard.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			Scene scene = new Scene(overviewPage);
			stage.setResizable(false);
			stage.setTitle("Facilitation de lien fournissuer");
			DashboardController controller = loader.getController();
			controller.setMainApp(this);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createAccountWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/CreateAccount.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			Scene scene = new Scene(overviewPage);
			stage.setResizable(false);
			stage.setTitle("Créer un compte");
			CreateAccountController controller = loader.getController();
			controller.setMainApp(this);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
