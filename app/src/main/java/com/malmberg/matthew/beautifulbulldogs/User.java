package com.malmberg.matthew.beautifulbulldogs;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matthew on 9/26/2017.
 */

public class User extends RealmObject implements Serializable {
    @PrimaryKey
    private String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
