package com.ccproject.cloud.cloudclubbing;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Customer {
    int                     id;
    public  String          name;
    int                     age;
    public  String          password;
    int                     photoId;
    int                     commandId;
    public String           email;
    private PaiementCard    card;



    public Customer(int my_id, String my_name, String my_email, String my_password) {

        this.id = my_id;
        this.name = my_name;
        this.email = my_email;
        this.password = my_password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
