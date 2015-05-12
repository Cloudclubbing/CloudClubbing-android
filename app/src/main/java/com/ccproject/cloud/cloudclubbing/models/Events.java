package com.ccproject.cloud.cloudclubbing.models;

import java.util.Date;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Events {
    int                 id;
    private String      name;
    private Date        eventDate;
    private String      description;
    int                 photoId;

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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
