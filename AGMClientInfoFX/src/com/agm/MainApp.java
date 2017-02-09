package com.agm;

import java.io.IOException;

import com.agm.comp.LoginDialog;
import com.agm.model.Contact;
import com.agm.service.Service;
import com.agm.service.impl.ServiceImpl;
import com.agm.view.ContactDetailController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<Contact> contacts = FXCollections.observableArrayList();
	private Service service;
	
	public MainApp() {
		this.service = new ServiceImpl();
		this.contacts.addAll(this.service.getAllContacts());
	}
	
	/*
	 * Getters and Setters
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public ObservableList<Contact> getContacts() {
        return contacts;
    }
	
	/*
	 * Methods
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Platform.setImplicitExit(true);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Client Info");
		
		this.initRootLayout();
//		new LoginDialog();
//		this.showLoginDialog();
		this.showContactDetailOverview();
	}
	
	/**
	 * Initializes the Main Layout
	 */
	public void initRootLayout() {
		try {
			//Load MainStage from fxml
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainStage.fxml"));
			this.rootLayout = loader.load();
			
			// Show the scene containing the main layout
			final Scene scene = new Scene(this.rootLayout);
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
			loader.setLocation(MainApp.class.getResource("view/ContactDetail.fxml"));
			final AnchorPane clientOverView = loader.load();
			
			//Set CLientOverview into the center of main layout
			this.rootLayout.setCenter(clientOverView);
			
			 // Give the controller access to the main app.
	        final ContactDetailController controller = loader.getController();
	        controller.setMainApp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
