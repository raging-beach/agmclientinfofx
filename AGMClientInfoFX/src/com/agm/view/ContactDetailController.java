package com.agm.view;

import com.agm.MainApp;
import com.agm.model.Contact;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactDetailController {
	
	private MainApp mainApp;

	@FXML
	private TableView<Contact> contactTable;
	@FXML
	private TableColumn<Contact, String> firstNameColumn;
	@FXML
	private TableColumn<Contact, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label primaryContactLabel;
	@FXML
	private Label secondaryContactLabel;
	@FXML
	private Label notesLabel;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ContactDetailController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	// If using other data type property, add asObject() at the end
        this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
        
        this.showContactDetails(null);
        this.contactTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactDetails(newValue));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        this.contactTable.setItems(mainApp.getContacts());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showContactDetails(Contact contact) {
        if (contact != null) {
            // Fill the labels with info from the person object.
            this.firstNameLabel.setText(contact.getFirstName());
            this.lastNameLabel.setText(contact.getLastName());
            this.primaryContactLabel.setText(contact.getContactNumber());
            this.secondaryContactLabel.setText(contact.getSecondaryContactNumber());

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	this.firstNameLabel.setText("");
        	this.lastNameLabel.setText("");
        	this.primaryContactLabel.setText("");
        	this.secondaryContactLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void deleteContact() {
        final int selectedIndex = this.contactTable.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex >= 0) {
        	this.contactTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewContact() {
        final Contact tempContact = new Contact();
        final boolean okClicked = this.mainApp.showContactEditDialog(tempContact);
        if (okClicked) {
            this.mainApp.getContacts().add(tempContact);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        final Contact selectedContact = this.contactTable.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            boolean okClicked = this.mainApp.showContactEditDialog(selectedContact);
            if (okClicked) {
                showContactDetails(selectedContact);
            }
        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
