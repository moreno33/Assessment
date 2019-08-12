package com.emarkall.assessment.domain;

import android.os.Parcelable;

public class User implements Parcelable {

    /**
     * Here is the data of the user
     */
    private String firstName, lastName, avatarUrl;

    //Default constructor
    public User(){}

    //Parameterized constructor

    public User(String firstName, String lastName, String avatarUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }


    //GETTERS AND SETTERS

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
