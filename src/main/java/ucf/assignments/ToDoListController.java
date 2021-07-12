package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ToDoListController implements Initializable {

    @FXML
    private CheckBox EventStatus;

    @FXML
    private TextField EventDescription;

    @FXML
    private DatePicker EventDate;

    @FXML
    private TableView<Event> myToDoTable;

    @FXML
    private TableColumn<Event, String> DueDateColumn;

    @FXML
    private TableColumn<Event, String> EventDescriptionColumn;

    @FXML
    private TableColumn<Event, Boolean> EventStatusColumn;

    @FXML Button AddEventButton;

    @FXML Button ModifyEventButton;

    @FXML Button DeleteEventButton;

    @FXML Button ChangeStatusButton;

    @FXML Button DeleteAllButton;

    @FXML Button ShowAllButton;

    @FXML Button ShowCompletedButton;

    @FXML Button ShowIncompleteButton;

    @FXML
    private ListView<Event> myToDoList;

    @FXML
    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private ObservableList<Event> master;

    @FXML
    public void AddEventButtonClicked(ActionEvent actionEvent) throws IOException {
        // first check if TextField is empty
        String error;
        String date = EventDate.getEditor().getText();
        // also check if date is valid
        if (!CheckEmptyTextField()){
            // Opens new window, tell user event description
            error = "Error: Event description cannot be empty.";
                showErrorMessage(error);
        }
        if (!CheckEmptyDatePicker()){
            error = "Error: Date cannot be empty.";
                showErrorMessage(error);
        }
        else if (CheckLengthTextField()){
            error = "Error: The description cannot be more than 256 characters long.";
                showErrorMessage(error);
        }
        else if (!ValidateGregorianDate(date)){
            // Opens new window, tell user date is invalid
            error = "Error: Invalid Date.";
                showErrorMessage(error);
        }
        else if (CheckEmptyTextField() && ValidateGregorianDate(date)){
            // add event since date and description are valid
            AddItemsToList();
        }
        // update master list to include newly added event
        updateMaster();
    }
    @FXML
    public void DeleteEventButtonClicked(ActionEvent actionEvent) {
        // gets the index of selected item on ListView
        int selectedIndex = myToDoTable.getSelectionModel().getSelectedIndex();
        // Removes said index from listview if something is selected
        if (selectedIndex >= 0)
            myToDoTable.getItems().remove(selectedIndex);
        updateMaster();
    }
    @FXML
    public void DeleteAllButtonClicked(ActionEvent actionEvent) {
        // clears enire list
        myToDoTable.getItems().clear();
    }
    @FXML
    public void EditButtonClicked(ActionEvent actionEvent) throws IOException {
        // only if date is valid
        String date = EventDate.getEditor().getText();
        if (ValidateGregorianDate(date)) {
            //get the text field, set the textfield to the itemDescription
            myToDoTable.getSelectionModel().getSelectedItem().setDescription(EventDescription.getText());
            // get date picker, set value to itemEventDate
            myToDoTable.getSelectionModel().getSelectedItem().setEventDate(formatDate(EventDate.getEditor().getText()));
            // get checkbox status, set value to itemStatus
            myToDoTable.getSelectionModel().getSelectedItem().setEventStatus(EventStatus.isSelected());
            // refreshes TableView, allows user to see changes
            myToDoTable.refresh();
            // clear user input fields
            ItemIsUnselected();
            // originally I would like to clear selection aka deselect currently selected item
            // however I could not figure find it online
            // I decided to just disable the modify button so that you can add new events
            // after the user input fields are cleared
            ModifyEventButton.setDisable(true);
        } else { // shows error message
            String error = "Error: Invalid Date.";
            showErrorMessage(error);
        }
    }

    @FXML
    public void ShowCompletedButtonClicked(ActionEvent actionEvent) throws IOException {
        // there has to be something in the master list
        if (master != null) {
            // filter for true
            Predicate<Event> trueStatus = Event::getEventStatus;
            // converts master to a filtered(true) list
            List<Event> filtered = master.stream().toList().stream().filter(trueStatus).collect(Collectors.toList());
            // opens new window
            showFilteredList(filtered);
        }
    }
    @FXML
    public void ShowIncompleteButtonClicked(ActionEvent actionEvent) throws IOException {
        // there has to be something in the master list
        if (master != null) {
            // filter for false
            Predicate<Event> falseStatus = event -> !event.getEventStatus();
            // converts master to a filtered(false) list
            List<Event> filtered = master.stream().toList().stream().filter(falseStatus).collect(Collectors.toList());
            // opens new window
            showFilteredList(filtered);
       }
    }
    @FXML
    public void ShowAllButtonClicked(ActionEvent actionEvent) throws IOException {
        if (master != null) {
            // creates a new list from master
            List<Event> filtered = master.stream().toList().stream().toList();
            // open new window view
            showFilteredList(filtered);
        }
    }
    @FXML
    public void ImportButtonClicked(ActionEvent actionEvent) throws FileNotFoundException {
        // creates new window
        Window stage = EventDescription.getScene().getWindow();
        // set window name
        fileChooser.setTitle("RobbinHood.exe");
        // set extensions for file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Documents", "*.txt"));
        // makes sure file exists before reading it
        File file = fileChooser.showOpenDialog(stage);
        if (file!=null) {
            // call fucntion to read data from file
            ReadFile(file);
        }
        // update master list to incldue imported items
        updateMaster();
    }
    @FXML
    public void ExportButtonClicked(ActionEvent actionEvent) throws FileNotFoundException {
        // creates new window
        Window stage = EventDescription.getScene().getWindow();
        // set window name
        fileChooser.setTitle("Emotet.trojan");
        // set default file name
        fileChooser.setInitialFileName("savelist");
        // set extensions for file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Documents", "*.txt"));
        // Shows save dialog
        File file = fileChooser.showSaveDialog(stage);
        // checks if file exists
        if (file!=null){
            // calls function to save data to said file
            saveDataToFile(convertDataToText(), file);
        }
    }
    @FXML
    public void ChangeStatusButtonClicked(ActionEvent actionEvent) {
        // gets selected item on TableView
        Event item = myToDoTable.getSelectionModel().getSelectedItem();
        // item must be selected before changing status
        int selectedIndex = myToDoTable.getSelectionModel().getSelectedIndex();
        // makes sure that an item is selected
        if (selectedIndex >= 0) {
            // sets status to the opposite of what it was
            item.setEventStatus(!item.getEventStatus());
            // updates TableView which allows user to see changed data
            myToDoTable.refresh();
            // update changed event in master list
            updateMaster();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // sets column data
        EventDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        DueDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        EventStatusColumn.setCellValueFactory(new PropertyValueFactory<>("eventStatus"));

        // only allows modification and deletion of a selected item
        myToDoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (myToDoTable.getSelectionModel().getSelectedIndex() >= 0) {
                // if an item is selected, buttons are enabled
                ModifyEventButton.setDisable(false);
                DeleteEventButton.setDisable(false);
                ChangeStatusButton.setDisable(false);
                ItemIsSelected();
            } else {
                // if none are selected, buttons are disabled
                ModifyEventButton.setDisable(true);
                DeleteEventButton.setDisable(true);
                ChangeStatusButton.setDisable(true);
                ItemIsUnselected();
            }
        });

        // whenever a change in textfield is detected
        BooleanBinding addEvent = new BooleanBinding() {
            {
                super.bind(EventDescription.textProperty(), EventDate.editorProperty());
            }
            @Override
            protected boolean computeValue() {
                // if both input fields are empty button will be disable
                if (EventDescription.getText().isEmpty() || EventDate.getEditor().getText().isEmpty() || !ModifyEventButton.isDisable()) {
                    return true;
                }
                // else if both are filled button will be enabled. However...
                // it prevents the user from adding the modified event as a new event
                else return EventDescription.getText().isEmpty() && EventDate.getEditor().getText().isEmpty() && !ModifyEventButton.isDisable();
                // always disabled otherwise
            }
        };
        // binds Boolean to button
        AddEventButton.disableProperty().bind(addEvent);

        // whenever an item exits in TableView
        BooleanBinding notEmpty = new BooleanBinding() {
            {
                super.bind(myToDoTable.getItems());
            }
            @Override
            protected boolean computeValue() {
                // if table is empty, disable button
                return myToDoTable.getItems().isEmpty();
            }
        };
        // Buttons cannot be pressed if there is nothing the list to delete
        DeleteAllButton.disableProperty().bind(notEmpty);
        ShowAllButton.disableProperty().bind(notEmpty);
        ShowCompletedButton.disableProperty().bind(notEmpty);
        ShowIncompleteButton.disableProperty().bind(notEmpty);
    }

    public boolean ValidateGregorianDate(String date){
        // set initial validity to false
        String formatted = getYear(date) + "-" + getMonth(date) + "-" + getDay(date);
        boolean valid;
        try {
            LocalDate.parse(formatted, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
            valid = true;
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }

    public String formatDate(String date){
        String[] array = date.split("/", 3);
        String formatted = array[2] + "-" + array[0] + "-" + array[1];
        return String.valueOf(LocalDate.parse(formatted, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT)));
    }

    // checks if item is empty
    private boolean CheckEmptyTextField(){
        return !Objects.equals(EventDescription.getText(), ""); // false if TextField is empty
        // true if TextField filled in
    }

    private boolean CheckLengthTextField(){
        //return true is length is too long
        // return false if below 256
        return EventDescription.getText().length() > 256;
    }

    // checks if item is empty
    private boolean CheckEmptyDatePicker(){
        return !EventDate.getEditor().getText().equals(""); // false if DatePicker is empty
        // true if DatePicker filled in
    }

    // gets year and returns it
    public int getYear(String date){
        String[] array = date.split("/", 3);
        return Integer.parseInt(array[2]);
    }

    // gets month and returns it
    public int getMonth(String date){
        String[] array = date.split("/", 3);
        return Integer.parseInt(array[0]);
    }

    // gets day and returns it
    public int getDay(String date){
        String[] array = date.split("/", 3);
        return Integer.parseInt(array[1]);
    }

    private void ClearTextField(){
        // clear textfield
        EventDescription.clear();
    }

    private void ClearDatePicker(){
        // clear datepicker
        EventDate.setValue(null);
    }
    private void ClearCheckBox(){
        // clears checkbox
        EventStatus.setSelected(false);
    }

    private void ItemIsSelected(){
        Event item = myToDoTable.getSelectionModel().getSelectedItem();
        // set the TextField and DatePicker
        EventDescription.setText(item.getDescription());
        EventDate.setValue(LocalDate.parse(item.getEventDate()));
        EventStatus.setSelected(item.getEventStatus());
    }

    private void ItemIsUnselected(){
        // clears all user inputs
        ClearTextField();
        ClearDatePicker();
        ClearCheckBox();
    }

    private void AddItemsToList(){
        // adds item to tableView
        myToDoTable.getItems().add(new Event(EventDescription.getText(), EventDate.getValue().toString(), EventStatus.isSelected()));
        // clears user inputs
        ItemIsUnselected();
    }

    private void saveDataToFile(String data, File file) throws FileNotFoundException {
        // saves data to file
        PrintWriter writer;
        writer = new PrintWriter(file);
        writer.println(data);
        writer.close();
    }

    public String convertDataToText(){
        // create data string variable
        StringBuilder data = new StringBuilder();
        // copy data from TableView
        ObservableList<Event> list = myToDoTable.getItems();
        // Loop through Observable list and add to data String
        for (Event item : list){
            // appends data string
            data.append(item.getDescription()).append("☆").append(item.getEventDate()).append("☆").append(item.getEventStatus()).append("\n");
        }
        // returns string data
        return data.toString();
    }

    private void updateMaster(){
        master = myToDoTable.getItems();
    }

    private void formatListView(){
       myToDoList.setCellFactory (param -> new ListCell<>() {
           @Override
           protected void updateItem(Event item, boolean empty) {
               super.updateItem(item, empty);

               if (empty || item == null || item.getDateAndDescription() == null) {
                   setText(null);
               } else {
                   setText(item.getDateAndDescription());
               }
           }
       });
    }

    private void ReadFile(File data) throws FileNotFoundException {
        Scanner reader = new Scanner(data);
        while (reader.hasNext()) {
            String fileData = reader.nextLine();
            // splits String data
            String[] array = fileData.split("☆", 3);
            // parses boolean status
            boolean status = Boolean.parseBoolean(array[2]);
            // adds to TableView
            myToDoTable.getItems().add(new Event(array[0], array[1], status));
        }
    }

    private void showFilteredList(List<Event> filtered) throws IOException {
        // locate FXMl file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FilteredList.fxml"));
        // setup scene
        Stage stage = new Stage(StageStyle.DECORATED);
        // load scene
        stage.setScene(new Scene(loader.load()));
        // set title
        stage.setTitle("Filtered.exe");
        // pass parameter from ToDoList to FilteredList
        FilteredListController controller = loader.getController();
        // pass a list of type <Event>
        controller.transferData(filtered);
        // Open new window
        stage.show();
    }

    private void showErrorMessage(String message) throws IOException {
        // locate FXMl file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorMessage.fxml"));
        // setup scene
        Stage stage = new Stage(StageStyle.DECORATED);
        // load scene
        stage.setScene(new Scene(loader.load()));
        // set title
        stage.setTitle("Error.exe");
        // pass parameter from ToDoList to ErrorMessage
        ErrorMessageController controller = loader.getController();
        // pass a list of type <Event>
        controller.setErrorMessage(message);
        // Open new window
        stage.show();
    }
}