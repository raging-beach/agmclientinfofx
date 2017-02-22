package com.agm.comp;

import com.agm.MainApp;
import com.agm.model.SystemUser;
import com.agm.service.Service;
import com.agm.service.impl.ServiceImpl;
import com.agm.utils.CommonHelper;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.util.Pair;

public class LoginDialog {
	
	private Service service = new ServiceImpl();

	public LoginDialog() {
		super();
		this.showLoginDialog();
	}
	
	private void showLoginDialog() {
		final Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("AGM Services System");
		dialog.setHeaderText("Ready to take on today...");

		// Set the icon (must be included in the project).
//		dialog.setGraphic(new ImageView(this.getClass().getResource("public/images/login.png").toString()));

		// Set the button types.
		final ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		final ButtonType cancelButtonType = new ButtonType("Quit", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

		// Create the username and password labels and fields.
		final GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		final TextField username = new TextField();
		username.setPromptText("e.g. fname");
		final PasswordField password = new PasswordField();
		password.setPromptText("xxxxxx");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);
		final Label errLabel = new Label();
		errLabel.setTextFill(Color.web("#0076a3"));;
		grid.add(errLabel, 0, 3, 2, 1);

		// Enable/Disable login button depending on whether a username was entered.
		final Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
		final Node cancelButton = dialog.getDialogPane().lookupButton(cancelButtonType);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    ((javafx.scene.Node) loginButton).setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());
		
		dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show(); 
        
		loginButton.addEventFilter(EventType.ROOT, e -> {
			if (e.getEventType().equals(ActionEvent.ACTION)) {
				e.consume();
				final SystemUser user = this.service.getLoginError(username.getText(), password.getText());
				if (CommonHelper.hasValidValue(user.getLoginError())) {
					errLabel.setText(user.getLoginError());
				} else {
					dialog.close();
					final MainApp mainApp = new MainApp();
					mainApp.setLoggedInUser(user);
					mainApp.start(mainApp.getPrimaryStage());
//					mainApp.showContactDetailOverview();
				}
			}
		});
		
		cancelButton.addEventFilter(EventType.ROOT, e -> {
			if (e.getEventType().equals(ActionEvent.ACTION)) {
				e.consume();
				dialog.close();
				Platform.exit();
			}
		});
	}
	
}
