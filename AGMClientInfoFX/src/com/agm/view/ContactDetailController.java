package com.agm.view;

import com.agm.MainApp;
import com.agm.model.Contact;

import javafx.fxml.FXML;
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
        this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        
        this.showPersonDetails(null);
        this.contactTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
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
    private void showPersonDetails(Contact contact) {
        if (contact != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(contact.getFirstNameStr());
            lastNameLabel.setText(contact.getLastNameStr());
            primaryContactLabel.setText(contact.getContactNumberStr());
            secondaryContactLabel.setText(contact.getSecondaryContactNumberStr());

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            primaryContactLabel.setText("");
            secondaryContactLabel.setText("");
        }
    }
}
