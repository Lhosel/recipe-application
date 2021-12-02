package ca.gbc.recipeproject.model;

import java.util.Date;

public class Events extends BaseEntity {

    // assignment 2 addition

    private String eventName;
    private Date eventDate;
    private User user;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
