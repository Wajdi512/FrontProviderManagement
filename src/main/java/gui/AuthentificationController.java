package gui;

public class AuthentificationController {

	private Main mainApp;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void goToDashboard() {
		mainApp.dashboardWindow();
	}

	public void goToCreateAccount() {
		mainApp.createAccountWindow();
	}

}
