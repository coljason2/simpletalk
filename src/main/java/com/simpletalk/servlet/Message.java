package com.simpletalk.servlet;


import org.directwebremoting.annotations.DataTransferObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@DataTransferObject
public class Message implements Serializable {

    private static final long serialVersionUID = 64646441L;

    private String username;
    private String text;
    private String date;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Message(String newtext) {
        text = newtext;
        if (text.length() > 256) {
            text = text.substring(0, 256);
        }
        date = sdFormat.format(new Date());
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
