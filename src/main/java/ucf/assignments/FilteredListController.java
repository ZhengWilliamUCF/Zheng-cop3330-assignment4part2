package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FilteredListController implements Initializable {
    @FXML
    private TableView<Event> FilteredStatusTable;

    @FXML
    private TableColumn<Event, String> DueDateColumn;

    @FXML
    private TableColumn<Event, String> EventDescriptionColumn;

    @FXML
    private TableColumn<Event, Boolean> IsCompletedColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // sets up column data
        EventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        DueDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        IsCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("eventStatus"));
    }

    public void transferData(List<Event> filtered){
        FilteredStatusTable.getItems().addAll(filtered);
    }
}
