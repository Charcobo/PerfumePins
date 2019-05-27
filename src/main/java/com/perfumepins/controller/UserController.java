/**
 * @author Charlie Logan
 * @date May 26, 2019
 */

package com.perfumepins.controller;

import com.perfumepins.model.Pin;
import com.perfumepins.model.User;
import com.perfumepins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("perfume/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET - http://<hostname>/perfume/user/
    // Returns a list of all users
    @GetMapping("/")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET - http://<hostname>/perfume/user?username=<username>
    // Returns a list of users containing the specified username
    @GetMapping("")
    public @ResponseBody ArrayList<User> getUserByUsername(@RequestParam (value = "username") String username) {
        return userRepository.findUsersByUsernameContains(username);
    }

    // GET - http://<hostname>/perfume/user/<userId>
    // Returns a single user specified by the userId
    @GetMapping("/{userId}")
    public @ResponseBody Optional<User> getUserById(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }

    // GET - http://<hostname>/perfume/user/<userId>/pins
    // Returns a sorted list of pins owned by the user specified by userId
    @GetMapping("/{userId}/pins")
    public @ResponseBody List<Pin> getUserPins(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Pin> userPins = user.get().getPins();
        Comparator<Pin> compareByPinId = (p1, p2) -> p1.getPinId().compareTo(p2.getPinId());
        Collections.sort(userPins, compareByPinId);
        return userPins;
    }

    // POST - http://<hostname>/perfume/user/add
    // Adds a user
    @PostMapping(value = "/add") //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addUser(
            @RequestBody @Valid User userData) {
        User newUser = new User();
        newUser.setUsername(userData.getUsername());
        userRepository.save(newUser);
        User user = userRepository.findById(newUser.getUserId()).get();
        return "New user " + user.getUsername() + " has been added.";
    }

    // POST - http://<hostname>/perfume/user/<userId>/pins/add
    // Adds a pin specified by request body to a user specified by userId
    @PostMapping(value = "/{userId}/pins/add") //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addPinToUserPins(
            @PathVariable Integer userId,
            @RequestBody Pin pin) {
        User user = userRepository.findById(userId).get();
        user.addPin(pin);
        userRepository.save(user);
        return "Pin " + pin.getPinId() + " added to " + user.getUsername() + "'s pin collection.";
    }

    // POST - http://<hostname>/perfume/user/<userId>/pins/add
    // Adds a pin specified by request body to a user specified by userId
    @PostMapping(value = "/{userId}/pins/add-all") //, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addPinsToUserPins(
            @PathVariable Integer userId,
            @RequestBody ArrayList<Pin> pins) {
        User user = userRepository.findById(userId).get();
        user.addPins(pins);
        userRepository.save(user);
        return "Pins added to " + user.getUsername() + "'s pin collection.";
    }

    // PUT/PATCH - http://<hostname>/perfume/user/<userId>
    // Updates a user specified by userId

    // DELETE - http://<hostname>/perfume/user/<userId>
    // Deletes a user specified by userId (Will need to clean up the cross table entries as well)

    // DELETE - http://<hostname>/perfume/user/<userId>/pin/<pinId>
    // Deletes a pin specified by pinId from a user specified by userId (But from which row if multiple?)
}
