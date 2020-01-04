package org.launchcode.spaday.data;

import org.launchcode.spaday.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserData {

    // Need a place to put the users as they are registered
    // Final - able to add and remove users, but not replace entire collection
    private static final Map<Integer, User> users = new HashMap<>();

    // Add a new user
    public static void add(User user) {
        users.put(user.getId(), user);
    }

}
