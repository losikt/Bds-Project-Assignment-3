package vut.feec.bpc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textFieldUserName;

    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    protected void onLoginButtonClick() {
        Connection conn =DBConnect.dbConnect("jdbc:postgresql://localhost:5432/project", "postgress", "bohuno");
        if (PwdHash.validCredentials(conn,textFieldUserName.getText(),passwordFieldPassword.getText())){
            welcomeText.setText("Match");
        }
        else{
            welcomeText.setText("L ratio");
        }
        DBConnect.dbDisconnect(conn);
    }
}