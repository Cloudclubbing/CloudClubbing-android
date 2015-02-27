package com.ccproject.cloud.cloudclubbing;

import java.util.List;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class NightClub {
    private String          description;
    private Photos          photo;
    private String          name;
    private List<Events> eventsList;
    private List<Newsfeeds> newsFeedsList;
    private List<Products>  productsList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photos getPhoto() {
        return photo;
    }

    public void setPhoto(Photos photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Events> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Events> eventsList) {
        this.eventsList = eventsList;
    }

    public List<Newsfeeds> getNewsFeedsList() {
        return newsFeedsList;
    }

    public void setNewsFeedsList(List<Newsfeeds> newsFeedsList) {
        this.newsFeedsList = newsFeedsList;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }
}
