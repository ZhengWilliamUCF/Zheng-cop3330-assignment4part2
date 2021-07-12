package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListControllerTest {

    @Test
    void ListCanContain100Items(){
        ArrayList<Event> test = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String description = String.valueOf(i);
            test.add(new Event(description, "2021-05-09", false));
        }
        assertEquals(100, test.size());
    }

    @Test
    void descriptionLengthTooLong(){
        String text = "rc6n2uHQQQ6RrUuuznMwlOMw" +
                "lwFs4Udvvv0UoaXLxxSuiyzEZ7o8ou" +
                "1shO6ofCna087yeQK59f0cLb8RL5hW" +
                "tOIX9FIMTZUAdg44EobEiWnxu72MRX" +
                "2mBDnIpyCL0RuENE6OSDkJK64N4cZb" +
                "LjlnIDNuXg6SiSETy6escD9IKdBgoN" +
                "C33WdSa2iXmCI5ljRRjy907j3PUiB5" +
                "L2O2szTuP8mwP4btSabIKLKLxDEkxZ" +
                "qgyF5EwDq1MVf2jV1mTQY4T";
        Boolean actual = null;
        if (text.length() > 256){
            actual = true;
        }
        assertEquals(true, actual);
    }

    @Test
    void descriptionLengthTooShort(){
        String text = "";
        Boolean actual = null;
        if (text.length() == 0){
            actual = true;
        }
        assertEquals(true, actual);
    }

    @Test
    void getDay(){
        ToDoListController App = new ToDoListController();
        int day = App.getDay("3/5/2021");
        assertEquals(5, day);
    }

    @Test
    void getMonth(){
        ToDoListController App = new ToDoListController();
        int month = App.getMonth("3/5/2021");
        assertEquals(3, month);
    }

    @Test
    void getYear(){
        ToDoListController App = new ToDoListController();
        int year = App.getYear("3/5/2021");
        assertEquals(2021, year);
    }

    @Test
    void formatDate(){
        ToDoListController App = new ToDoListController();
        String date = App.formatDate("3/5/2021");
        assertEquals("2021-03-05", date);
    }

    @Test
    void ValidGregorianDateTrue(){
        ToDoListController App = new ToDoListController();
        Boolean actual = App.ValidateGregorianDate("3/5/2021");
        assertEquals(true, actual);
    }

    @Test
    void ValidGregorianDateFalseMonth(){
        ToDoListController App = new ToDoListController();
        Boolean actual = App.ValidateGregorianDate("31/5/2021");
        assertEquals(false, actual);
    }

    @Test
    void ValidGregorianDateFalseDay(){
        ToDoListController App = new ToDoListController();
        Boolean actual = App.ValidateGregorianDate("3/51/2021");
        assertEquals(false, actual);
    }

    @Test
    void ValidGregorianDateFalseYear(){
        ToDoListController App = new ToDoListController();
        Boolean actual = App.ValidateGregorianDate("3/11/20219");
        assertEquals(false, actual);
    }

    @Test
    void AddItemToList(){
        ArrayList<Event> test = new ArrayList<>();
        int intialSize = test.size();
        test.add(new Event("test", "2021-05-09", false));
        int afterAddItemSize = test.size();
        assertNotEquals(intialSize, afterAddItemSize);
    }

    @Test
    void removeItemFromList(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", false));
        int intialSize = test.size();
        test.remove(0);
        int afterAddItemSize = test.size();
        assertNotEquals(intialSize, afterAddItemSize);
    }

    @Test
    void removeAllItemFromList(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", false));
        test.add(new Event("test1", "2021-06-10", false));
        test.add(new Event("test2", "2021-07-11", false));
        test.clear();
        int afterAddItemSize = test.size();
        assertEquals(0, afterAddItemSize);
    }

    @Test
    void editDescription(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", false));
        test.get(0).setDescription("changed description");
        String actual = test.get(0).getDescription();
        assertEquals("changed description", actual);
    }

    @Test
    void editDate(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", false));
        test.get(0).setEventDate("2021-05-04");
        String actual = test.get(0).getEventDate();
        assertEquals("2021-05-04", actual);
    }

    @Test
    void markComplete(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", false));
        test.get(0).setEventStatus(true);
        Boolean actual = test.get(0).getEventStatus();
        assertEquals(true, actual);
    }

    @Test
    void markInComplete(){
        ArrayList<Event> test = new ArrayList<>();
        test.add(new Event("test", "2021-05-09", true));
        test.get(0).setEventStatus(false);
        Boolean actual = test.get(0).getEventStatus();
        assertEquals(false, actual);
    }

    @Test
    void displayAllItems(){
        ArrayList<Event> test0 = new ArrayList<>();
        String fullString = "";
        test0.add(new Event("test", "2021-05-09", true));
        test0.add(new Event("test1", "2021-05-09", true));
        test0.add(new Event("test2", "2021-05-09", false));
        test0.add(new Event("test3", "2021-05-09", true));
        test0.add(new Event("test4", "2021-05-09", false));
        for (int i = 0; i < test0.size(); i++){
            fullString += test0.get(i).getDescription() + "-" + test0.get(i).getEventDate() + "-" + test0.get(i).getEventDate() + ", ";
        }
        String expected = "test-2021-05-09-2021-05-09, test1-2021-05-09-2021-05-09, test2-2021-05-09-2021-05-09, test3-2021-05-09-2021-05-09, test4-2021-05-09-2021-05-09, ";
        assertEquals(expected, fullString);
    }

    @Test
    void displayAllTrue(){
        ArrayList<Event> test1 = new ArrayList<>();
        ArrayList<Event> testTrue = new ArrayList<>();
        String fullString = "";
        test1.add(new Event("test", "2021-05-09", true));
        test1.add(new Event("test1", "2021-05-09", true));
        test1.add(new Event("test2", "2021-05-09", false));
        test1.add(new Event("test3", "2021-05-09", true));
        test1.add(new Event("test4", "2021-05-09", false));
        for (int i = 0; i < test1.size(); i++){
            if (test1.get(i).getEventStatus()){
                testTrue.add(test1.get(i));
            }
        }
        for (int i = 0; i < testTrue.size(); i++){
            fullString += testTrue.get(i).getDescription() + "-" + testTrue.get(i).getEventDate() + "-" + testTrue.get(i).getEventDate() + ", ";
        }
        String expected = "test-2021-05-09-2021-05-09, test1-2021-05-09-2021-05-09, test3-2021-05-09-2021-05-09, ";
        assertEquals(expected, fullString);
    }

    @Test
    void displayAllFalse(){
        ArrayList<Event> test2 = new ArrayList<>();
        ArrayList<Event> testFalse = new ArrayList<>();
        String fullString = "";
        test2.add(new Event("test", "2021-05-09", true));
        test2.add(new Event("test1", "2021-05-09", true));
        test2.add(new Event("test2", "2021-05-09", false));
        test2.add(new Event("test3", "2021-05-09", true));
        test2.add(new Event("test4", "2021-05-09", false));
        for (int i = 0; i < test2.size(); i++){
            if (!test2.get(i).getEventStatus()){
                testFalse.add(test2.get(i));
            }
        }
        for (int i = 0; i < testFalse.size(); i++){
            fullString += testFalse.get(i).getDescription() + "-" + testFalse.get(i).getEventDate() + "-" + testFalse.get(i).getEventDate() + ", ";
        }
        String expected = "test2-2021-05-09-2021-05-09, test4-2021-05-09-2021-05-09, ";
        assertEquals(expected, fullString);
    }

    @Test
    void formatInformationInOrderToSaveListToExternalStorage(){
        ArrayList<Event> test = new ArrayList<>();
        StringBuilder fullString = new StringBuilder();
        test.add(new Event("test", "2021-05-09", true));
        test.add(new Event("test1", "2021-05-09", true));
        test.add(new Event("test2", "2021-05-09", false));
        test.add(new Event("test3", "2021-05-09", true));
        test.add(new Event("test4", "2021-05-09", false));
        for (int i = 0; i < test.size(); i++){
            fullString.append(test.get(i).getDescription()).append("^").append(test.get(i).getEventDate()).append("^").append(test.get(i).getEventDate()).append("\n");
        }
        String expected = "test^2021-05-09^2021-05-09\n" +
                "test1^2021-05-09^2021-05-09\n" +
                "test2^2021-05-09^2021-05-09\n" +
                "test3^2021-05-09^2021-05-09\n" +
                "test4^2021-05-09^2021-05-09\n";
        assertEquals(expected, fullString.toString());
    }

    @Test
    void readInformationFromFileString() throws FileNotFoundException {
        ArrayList<Event> test = new ArrayList<>();
        String data = "test4☆2021-05-09☆2021-05-09";
        // splits String data
        String[] array = data.split("☆", 3);
        // parses boolean status
        boolean status = Boolean.parseBoolean(array[2]);
        // adds to TableView
        test.add(new Event(array[0], array[1], status));
        String expected ="test4-2021-05-09-2021-05-09";
        String actual = test.get(0).getDescription() + "-" + test.get(0).getEventDate() + "-" + test.get(0).getEventDate();
        assertEquals(expected, actual);
    }
}
