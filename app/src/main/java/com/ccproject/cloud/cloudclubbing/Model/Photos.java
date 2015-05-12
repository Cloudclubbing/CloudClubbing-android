package com.ccproject.cloud.cloudclubbing.Model;

import java.util.Date;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Photos {

    public int         id;
    public String      description;
    public String      link;
    public Date                date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
