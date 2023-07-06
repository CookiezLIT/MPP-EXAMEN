package client.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MessageAlert {
    public static void showMessage(Stage owner, Alert.AlertType type, String header, String text){
        Alert message=new Alert(type);
        if(text.length()>100) {
            message.setHeight(500);
            message.setWidth(800);
        }
        message.setHeaderText(header);
        if(type == Alert.AlertType.INFORMATION)
            message.setTitle("Mesaj de informare");
        if(type == Alert.AlertType.WARNING)
            message.setTitle("Mesaj de avertizare");
        if(type == Alert.AlertType.ERROR)
            message.setTitle("Mesaj de eroare!");
        message.setContentText(text);
        message.initOwner(owner);
        message.showAndWait();
    }
}


