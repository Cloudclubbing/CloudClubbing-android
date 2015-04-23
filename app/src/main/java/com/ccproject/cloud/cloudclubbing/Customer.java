package com.ccproject.cloud.cloudclubbing;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Customer {
    int                     id;
    public  String          name;
    public  String          login;
    public  String          password;
    public  String          pictureURL;
    int                     commandId;
    public  String          email;
    private PaiementCard    card;
    private static Customer instance = new Customer();



    private Customer() {

    }

    public static synchronized Customer getInstance() {
        return instance;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
