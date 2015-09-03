package com.ccproject.cloud.cloudclubbing.models;

import com.android.volley.toolbox.StringRequest;

import java.util.Date;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Events {
    int                 id;
    private String      name;
    private Date        eventDate;
    private String      description;
    private String      pictureUrl;

    public Events(String eventName, Date eventDate, String eventDescription, String pictureUrl) {
        this.name           = eventName;
        this.eventDate      = eventDate;
        this.description    = eventDescription;
        this.pictureUrl     = pictureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
