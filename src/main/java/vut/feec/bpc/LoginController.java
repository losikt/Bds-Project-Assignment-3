package vut.feec.bpc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textFieldUserName;

    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    protected void onLoginButtonClick() {
        Connection conn =DBConnect.dbConnect("jdbc:postgresql://localhost:5432/project", "postgress", "bohuno");
        FXMLLoader FXl = new FXMLLoader();
        Scene scene = null;
        Stage stage = new Stage();
        Stage stageOld = null;
        switch (PwdHash.validCredentials(conn,textFieldUserName.getText(),passwordFieldPassword.getText())){
            case 1:
                FXl.setLocation(LoginWindow.class.getResource("admin-view.fxml"));
                try {
                    scene = new Scene(FXl.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stageOld = (Stage) buttonLogin.getScene().getWindow();
                stageOld.close();
                stage.show();
                break;
            case 2:
                FXl.setLocation(LoginWindow.class.getResource("librarian-view.fxml"));
                try {
                    scene = new Scene(FXl.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stageOld = (Stage) buttonLogin.getScene().getWindow();
                stageOld.close();
                stage.show();
                break;
            case 3:
                FXl.setLocation(LoginWindow.class.getResource("user-view.fxml"));
                try {
                    scene = new Scene(FXl.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stageOld = (Stage) buttonLogin.getScene().getWindow();
                stageOld.close();
                stage.show();
                break;
            case 4:
                FXl.setLocation(LoginWindow.class.getResource("editor-view.fxml"));
                try {
                    scene = new Scene(FXl.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stageOld = (Stage) buttonLogin.getScene().getWindow();
                stageOld.close();
                stage.show();
                break;
            case -1:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Username or password is not valid");
                alert.showAndWait();
                break;
        }
        DBConnect.dbDisconnect(conn);
    }
}