package edu.gsu.refreshx;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiResult {
    String message;

    List<User> users = new ArrayList<>();

    public ApiResult(){ }
    public String getMessage() {
        return message;
    }
    public List<User> getUsers() {
        return users;
    }

    public class User {
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;
        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;
        public User(){}

        @Override
        public String toString() {
            return "username: " + username + ", name: " + name ;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}