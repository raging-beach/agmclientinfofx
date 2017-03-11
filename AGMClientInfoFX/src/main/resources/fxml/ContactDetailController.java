package fxml;

import java.io.IOException;

import com.agm.MainApp;
import com.agm.model.Contact;
import com.agm.service.Service;
import com.agm.service.impl.ServiceImpl;
import com.agm.utils.Constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContactDetailController {
	
	private ObservableList<Contact> contacts = FXCollections.observableArrayList();
	private Service service;

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
    	this.service = new ServiceImpl();
    }
    
	public ObservableList<Contact> getContacts() {
        return contacts;
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	// If using other data type property, add asObject() at the end
		this.contacts.addAll(this.service.getAllContacts());
		this.contactTable.setItems(this.getContacts());
		
        this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
        
        this.showContactDetails(null);
        this.contactTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactDetails(newValue));
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
        final Contact selectedContact = this.contactTable.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0) {
        	this.service.deleteContact(selectedContact.getId());
        	this.contactTable.getItems().remove(selectedIndex);
        	//TODO: error message when delete failed
        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
//            alert.initOwner(this);
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
        final boolean okClicked = this.showContactEditDialog(tempContact);
        if (okClicked) {
            this.getContacts().add(tempContact);
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
            boolean okClicked = this.showContactEditDialog(selectedContact);
            if (okClicked) {
                showContactDetails(selectedContact);
            }
        } else {
            // Nothing selected.
            final Alert alert = new Alert(AlertType.WARNING);
//            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showContactEditDialog(Contact contact) {
		try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        final FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource(Constants.CONTACT_EDIT_FXML));
	        final AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        final Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
//	        dialogStage.initOwner(primaryStage);
	        final Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        final ContactEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setContact(contact);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
