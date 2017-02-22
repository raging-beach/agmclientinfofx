package fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.agm.utils.Constants;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MainStageController implements Initializable {

	@FXML
	private AnchorPane centerPane;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setContent (AnchorPane anchorPane){
        final List<Node> nodes = anchorPane.getChildren();
        this.centerPane.getChildren().clear();
        this.centerPane.getChildren().addAll(nodes);
    }
    
    @FXML
    public void handleContactManagement() {
    	ContentNavigator.loadContent(Constants.CONTACT_DETAIL_FXML);
    }
}
