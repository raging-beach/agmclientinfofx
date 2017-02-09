package com.agm;

import java.io.IOException;

import com.agm.comp.LoginDialog;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Platform.setImplicitExit(true);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Client Info");
		
		this.initRootLayout();
		new LoginDialog();
//		this.showLoginDialog();
//		this.showClientOverview();
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
	
	public void showClientOverview() {
		try {
			//Load ClientOverview
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ClientOverviewScene.fxml"));
			final AnchorPane clientOverView = loader.load();
			
			//Set CLientOverview into the center of main layout
			this.rootLayout.setCenter(clientOverView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
