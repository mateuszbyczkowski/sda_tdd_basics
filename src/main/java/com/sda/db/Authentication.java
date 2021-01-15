package com.sda.db;

public class Authentication {
    private String loggedIn;
    private Database database;

    public Authentication(Database database) {
        this.database = database;
        this.loggedIn = null;
    }

    public void logout() {
        loggedIn = null;
    }

    public void login(User user) {
        if (user == null) {
            throw new AuthenticationException("Authentication failed.");
        }
        User userFromDB = database.findUser(user.getLogin());
        if (userFromDB == null || !userFromDB.getPassword().equals(user.getPassword())) {
            throw new AuthenticationException("Authentication failed.");
        }
        loggedIn = userFromDB.getLogin();
    }

    public String getLoggedIn() {
        return loggedIn;
    }

}
