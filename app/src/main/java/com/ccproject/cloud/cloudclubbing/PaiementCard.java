package com.ccproject.cloud.cloudclubbing;

import java.util.Date;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class PaiementCard {
    int             id;
    int             cardNum;
    int             customerId;
    private Date validity;
    private String  OwnerName;
    int             cryptogram;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public int getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(int cryptogram) {
        this.cryptogram = cryptogram;
    }
}
