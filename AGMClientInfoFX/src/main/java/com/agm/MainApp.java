package com.agm;

import java.io.IOException;

import com.agm.comp.LoginDialog;
import com.agm.model.Contact;
import com.agm.model.SystemUser;
import com.agm.service.Service;
import com.agm.service.impl.ServiceImpl;
import com.agm.utils.Constants;

import fxml.ContactDetailController;
import fxml.ContactEditDialogController;
import fxml.ContentNavigator;
import fxml.MainStageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	
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
	
	public void showLoginDialog() {
		
	}
	
	/**
	 * Initializes the Main Layout
	 */
	public void initRootLayout() {
		try {
			//Load MainStage from fxml
			final FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MainApp.class.getResource(Constants.MAIN_STAGE_FXML));
			this.rootLayout = (AnchorPane) loader.load(getClass().getResourceAsStream(Constants.MAIN_STAGE_FXML));
			
			// Show the scene containing the main layout
			final Scene scene = new Scene(this.rootLayout);
			final MainStageController controller = loader.getController();
	        ContentNavigator.setController(controller);

			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showContactDetailOverview() {
		try {
			
			//Load ClientOverview
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(Constants.CONTACT_DETAIL_FXML));
			final AnchorPane clientOverView = loader.load();
			MainStageController mainStageController = new MainStageController();
			//Set CLientOverview into the center of main layout
//			this.rootLayout.setCenter(clientOverView);
			final Scene scene = new Scene(this.rootLayout);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
			 // Give the controller access to the main app.
	        final ContactDetailController controller = loader.getController();
//	        controller.setMainApp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
