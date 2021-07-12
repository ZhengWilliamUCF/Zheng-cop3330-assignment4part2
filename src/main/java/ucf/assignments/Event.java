package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 William Zheng
 */

public class Event {
    private String description;
    private String eventDate;
    private boolean eventStatus;

    public Event(String description, String eventDate, Boolean eventStatus) {
        this.description = description;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
    }

    public String getDescription(){
        return description;
    }

    public String getEventDate(){
        return eventDate;
    }

    public Boolean getEventStatus(){
        return eventStatus;
    }

    public String getDateAndDescription(){
        return eventDate + ": " + description;
    }

    public void setEventStatus(Boolean eventStatus){
        this.eventStatus = eventStatus;
    }

    public void setEventDate(String eventDate){
        this.eventDate = eventDate;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
