package com.example.loborems.Interfaces;

import com.example.loborems.models.User;

import java.util.List;

public interface UserDOA {

    public void save(User user);        // Saves a user
    public void update(User user);     // Updates a user
    public void delete(User user);     // Deletes a user
    public List<User> getAll();        // Retrieves all users
    public User findClient(int id);    // Finds a user by ID

}
