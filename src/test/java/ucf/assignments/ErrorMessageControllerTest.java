package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessageControllerTest {

    @Test
    public void setErrorMessage() {
        String actual= "tests1";
        actual = "test3";
        assertEquals("test3", actual);
    }
}