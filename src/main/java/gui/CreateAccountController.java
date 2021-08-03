package gui;

public class CreateAccountController {

	private Main mainApp;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	public void goToLogin() {
		mainApp.mainWindow();
	}
	


}
