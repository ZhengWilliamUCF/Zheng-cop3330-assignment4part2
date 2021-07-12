package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FilteredListControllerTest {

    @Test
    void transferDataTestFromListToEmptyTableTrue() {
        ArrayList<Event> filtered = new ArrayList<>();
        int initSize = filtered.size();
        filtered.addAll(displayAllTrue());
        int addItemSize = filtered.size();
        assertNotEquals(initSize, addItemSize);
    }

    @Test
    void transferDataTestFromListToEmptyTableFalse() {
        ArrayList<Event> filtered = new ArrayList<>();
        int initSize = filtered.size();
        filtered.addAll(displayAllFalse());
        int addItemSize = filtered.size();
        assertNotEquals(initSize, addItemSize);
    }

    public ArrayList<Event> displayAllTrue(){
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
        return testTrue;
    }

    public ArrayList<Event> displayAllFalse() {
        ArrayList<Event> test2 = new ArrayList<>();
        ArrayList<Event> testFalse = new ArrayList<>();
        String fullString = "";
        test2.add(new Event("test", "2021-05-09", true));
        test2.add(new Event("test1", "2021-05-09", true));
        test2.add(new Event("test2", "2021-05-09", false));
        test2.add(new Event("test3", "2021-05-09", true));
        test2.add(new Event("test4", "2021-05-09", false));
        for (int i = 0; i < test2.size(); i++) {
            if (!test2.get(i).getEventStatus()) {
                testFalse.add(test2.get(i));
            }
        }
        return testFalse;
    }
}