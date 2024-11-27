package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import home.controllers.Authenticator;

public class LoginPageController implements Initializable {

    @FXML
    private Button btnLogin;
    
    @FXML
    private TextField textUserName;
    
    @FXML
    private PasswordField textPassword;
    	
    @FXML
    private HBox pageContainer;
    
    //my bad - the freaking mouse event
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
    	
        if (mouseEvent.getSource() == btnLogin) {
            String username = textUserName.getText();
            String password = textPassword.getText();
            if (Authenticator.login(username, password)) {
            	System.out.println("Login Successfully");
            	Stage stage = (Stage) btnLogin.getScene().getWindow();
            	stage.close();
            	loadStage("/fxml/Home.fxml");
            	
            	
            } else {
            	System.out.println("Login Failed!");
            }
        } 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    private Stage getStage() {
//    	return currStage;
//    }
//    private Stage currStage; 
}
