package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();

    //add
    public static void add(User newUser) {
        users.add(newUser);
    }

    //get all
    public static ArrayList<User> getAll() {
        return users;
    }

    //get by Id

    public static User getById(int userId) {
        User theUser = null;

        for (User aUser : users) {
            if (aUser.getUserId() == userId) {
                theUser = aUser;
            }
        }

        return theUser;
    }

    // remove
    public static void remove(int id){
        User theUser = null;
        theUser = getById(id);
        users.remove(theUser);
    }


}
