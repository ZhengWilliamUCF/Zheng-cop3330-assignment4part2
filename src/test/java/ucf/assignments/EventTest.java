package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getDescription() {
        Event testing = new Event("test", "2021-05-09", false);
        String actual = testing.getDescription();
        assertEquals("test", actual);
    }

    @Test
    void getEventDate() {
        Event testing = new Event("test", "2021-05-09", false);
        String actual = testing.getEventDate();
        assertEquals("2021-05-09", actual);
    }

    @Test
    void getEventStatus() {
        Event testing = new Event("test", "2021-05-09", false);
        Boolean actual = testing.getEventStatus();
        assertEquals(false, actual);
    }

    @Test
    void getDateAndDescription() {
        Event testing = new Event("test", "2021-05-09", false);
        String actual = testing.getDateAndDescription();
        assertEquals("2021-05-09: test", actual);
    }

    @Test
    void setEventStatus() {
        Event testing = new Event("test", "2021-05-09", false);
        testing.setEventStatus(true);
        Boolean actual = testing.getEventStatus();
        assertEquals(true, actual);
    }

    @Test
    void setEventDate() {
        Event testing = new Event("test", "2021-05-09", false);
        testing.setEventDate("2021-07-21");
        String actual = testing.getEventDate();
        assertEquals("2021-07-21", actual);
    }

    @Test
    void setDescription() {
        Event testing = new Event("test", "2021-05-09", false);
        testing.setDescription("test2");
        String actual = testing.getDescription();
        assertEquals("test2", actual);
    }
}