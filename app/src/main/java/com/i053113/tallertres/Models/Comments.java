package com.i053113.tallertres.Models;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class Comments {

    private int id;
    private String postId;
    private String  email;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
