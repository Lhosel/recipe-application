package ca.gbc.recipeproject.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENTS")
public class Events extends BaseEntity {

    // assignment 2 addition
    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
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
