package com.ccproject.cloud.cloudclubbing;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Customer {
    int                     id;
    private String          name;
    int                     age;
    private String          password;
    int                     photoId;
    int                     commandId;
    private PaiementCard    card;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public PaiementCard getCard() {
        return card;
    }

    public void setCard(PaiementCard card) {
        this.card = card;
    }

    public Customer() {
    }
}
