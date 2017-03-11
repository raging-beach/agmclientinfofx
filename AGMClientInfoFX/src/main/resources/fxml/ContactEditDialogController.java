package fxml;

import com.agm.model.Contact;
import com.agm.service.Service;
import com.agm.service.impl.ServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactEditDialogController {
	
	@FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField secondaryContactNumberField;
    
    
    private Stage dialogStage;
    private Contact contact;
    private boolean okClicked = false;
    private Service service;
    
    public ContactEditDialogController() {
    	this.service = new ServiceImpl();
	}
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setContact(Contact contact) {
        this.contact = contact;

        this.firstNameField.setText(contact.getFirstName());
        this.lastNameField.setText(contact.getLastName());
        this.contactNumberField.setText(contact.getContactNumber());
        this.secondaryContactNumberField.setText(contact.getSecondaryContactNumber());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (this.isInputValid()) {
            this.contact.setFirstName(this.firstNameField.getText());
            this.contact.setLastName(this.lastNameField.getText());
            this.contact.setContactNumber(this.contactNumberField.getText());
            this.contact.setSecondaryContactNumber(this.secondaryContactNumberField.getText());
            this.service.saveContact(this.contact);
            
            this.okClicked = true;
            this.dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            final Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
}
