package gui;

public class DashboardController {
	
	private Main mainApp;

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	public void seDeconnecter() {
		mainApp.mainWindow();
	}


}
