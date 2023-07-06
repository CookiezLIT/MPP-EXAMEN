package client.controller;


import model.Utilizator;
import services.AppException;
import services.IService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class LoginController {
    protected IService server;
    protected Stage stage;

    @FXML
    private TextField textFieldUsername;


    @FXML
    private void initialize() {
    }

    public void setStageAndService(Stage stage, IService service) {
        this.server = service;
        this.stage = stage;
    }

    @FXML
    private void buttonConectareClicked(ActionEvent actionEvent) {
        if (textFieldUsername.getText().isEmpty()) {
            MessageAlert.showMessage(null, Alert.AlertType.WARNING, "Eroare", "Nu ai introdus username-ul!");
        } else
            try {
                Utilizator utilizator = new Utilizator(textFieldUsername.getText());
                FXMLLoader fxmlLoader = new FXMLLoader();


                utilizator = server.login(utilizator, null);

            } catch (AppException e) {
                throw new RuntimeException(e);
            } finally {

            }
    }
}
