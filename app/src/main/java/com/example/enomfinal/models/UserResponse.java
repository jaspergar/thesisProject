package com.example.enomfinal.models;

import java.util.List;

public class UserResponse {

    private boolean error;
    private List<Users> users;

    public UserResponse(boolean error, List<Users> users) {
        this.error = error;
        this.users = users;
    }

    public boolean isError() {
        return error;
    }

    public List<Users> getUsers() {
        return users;
    }
}
