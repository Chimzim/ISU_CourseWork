package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class User_Controller {
    /**
     * connects to the user repository
     */
    @Autowired
    User_Repository db;

    /**
     * returns the user based on the ID
     * @param id
     * @return
     */
    @GetMapping("/User/{id}")
    Optional<User> getGuest(@PathVariable Integer id) {
        return db.findById(id);
    }

    /**
     * returns all the users in the database
     * @return
     */
    @RequestMapping("/users")
    List<User> hello1() {
        return db.findAll();
    }

    /**
     * creates a user and returns the user that was made
     * @param p
     * @return
     */
    @PostMapping("/user")
    User createUser(@RequestBody User p) {
        db.save(p);
        return p;
    }




}














