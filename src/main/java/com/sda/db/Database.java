package com.sda.db;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Database {
    private Map<String, User> users;

    public Database() {
        users = new HashMap<>();
    }

    public Database(List<User> userList) {
        users = userList.stream().collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    public void addUser(User user) {
        if (users.containsKey(user.getLogin())) {
            throw new UserExistsException("User already exists.");
        }
        users.put(user.getLogin(), user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void removeUser(String login) {
        users.remove(login);
    }

    public User findUser(String login) {
        return users.get(login);
    }


    public List<User> findByLogin2(String search) {
        List<User> r = new LinkedList<>();
        for (String login : users.keySet()) {
            if (login.contains(search)) {
                r.add(users.get(login));
            }
        }
        return r;
    }

    public List<User> findByLogin3(String search) {
        return users.values()
                .stream()
                .filter(u -> u.getLogin().contains(search))
                .collect(Collectors.toList());
    }

    public List<User> findByLogin(String login) {
        /*List<User> result = new LinkedList<>();
        for(String key : users.keySet()){
            if(key.contains(login)){
                result.add(users.get(key));
            }
        }
        return result;*/

        return users.entrySet()
                .stream()
                .filter(user -> user.getKey().contains(login))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void modifyUser(User modifiedUser) {
        if (!users.containsKey(modifiedUser.getLogin())) {
            throw new UserNotFoundException("User not found.");
        }
        users.put(modifiedUser.getLogin(), modifiedUser);
    }
}
