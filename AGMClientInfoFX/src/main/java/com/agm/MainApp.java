package com.agm;

import java.io.IOException;

import com.agm.comp.LoginDialog;
import com.agm.model.SystemUser;
import com.agm.utils.Constants;

import fxml.ContentNavigator;
import fxml.MainStageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootPane;
	
	private SystemUser loggedInUser;
	
	public MainApp() {
		
	}
	
	/*
	 * Getters and Setters
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public SystemUser getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(SystemUser loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/*
	 * Methods
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		if (this.loggedInUser != null) {
			Platform.setImplicitExit(true);
			if (primaryStage == null) {
				primaryStage = new Stage();
			}
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Client Info");
			
			this.initRootLayout();
		} else {
			new LoginDialog();
		}
	}
	
	/**
	 * Initializes the Main Layout
	 */
	public void initRootLayout() {
		try {
			//Load MainStage from fxml
			final FXMLLoader loader = new FXMLLoader();
			this.rootPane = (AnchorPane) loader.load(getClass().getResourceAsStream(Constants.MAIN_STAGE_FXML));
			
			// Show the scene containing the main layout
			final Scene scene = new Scene(this.rootPane);
			final MainStageController controller = loader.getController();
	        ContentNavigator.setController(controller);

			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
}
