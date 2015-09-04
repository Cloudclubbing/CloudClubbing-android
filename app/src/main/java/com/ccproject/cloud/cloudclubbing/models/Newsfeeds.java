package com.ccproject.cloud.cloudclubbing.models;

import java.util.Date;

/**
 * Created by priteshasvinetsakou on 28/02/15.
 */
public class Newsfeeds {

    private String              content;
    private String              content_type;
    private String              socialMedia_type;
    private String              date;
    private String              pictureURL;

    public Newsfeeds(String my_content, String my_contentType, String my_socialMedia_type, String  my_date, String my_pictureUrl) {
        this.content            =   my_content;
        this.content_type       =   my_contentType;
        this.socialMedia_type   =   my_socialMedia_type;
        this.date               =   my_date;
        this.pictureURL         =   my_pictureUrl;
    }



    public String getPictureURL() {
        return pictureURL;
    }

    public String getDate() {
        return date;
    }

    public String getSocialMedia_type() {
        return socialMedia_type;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getContent() {
        return content;
    }

}
