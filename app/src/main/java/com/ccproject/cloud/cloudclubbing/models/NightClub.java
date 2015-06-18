package com.ccproject.cloud.cloudclubbing.models;

import java.util.List;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class NightClub {
    private String          description;
    private Photos          photo;
    private String          name;
    private List<Events>    eventsList;
    private List<Newsfeeds> newsFeedsList;
    private List<Products>  productsList;
    private String          adress;
    private String          phone;
    private String          website;
    private static NightClub instance = new NightClub();


    private NightClub () {

    }

    public static synchronized NightClub getInstance() {
        return instance;
    }


    private NightClub(String name, String description, String adress, String phone, String website) {
        this.name           =   name;
        this.description    =   description;
        this.adress         =   adress;
        this.phone          =   phone;
        this.website        =   website;
    }

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
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
