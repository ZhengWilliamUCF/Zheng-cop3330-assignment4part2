package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorMessageController {
    @FXML
    private Label ErrorMessage;

    public void setErrorMessage(String message) {
        ErrorMessage.setText(message);
    }
}
