/**
 * @author Charlie Logan
 * @date May 26, 2019
 */

package com.perfumepins.controller;

import com.perfumepins.model.Pin;
import com.perfumepins.model.User;
import com.perfumepins.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("perfume/rest/v1/pin")
public class PinController {
    @Autowired
    private PinRepository pinRepository;

    // http://<hostname>/perfume/pin/
    // Returns a list of all pins
    @GetMapping("/")
    public @ResponseBody Iterable<Pin> getAllPins() {
        return pinRepository.findAll();
    }

    // http://<hostname>/perfume/pin?pinName=<partial pin name>
    // Returns a list of pins containing the specified pinName
    @GetMapping("")
    public @ResponseBody ArrayList<Pin> getPinsByPinName(@RequestParam (value = "pinName") String pinName) {
        return pinRepository.findPinsByPinNameContains(pinName);
    }

    // http://<hostname>/perfume/pin/<pinId>
    // Returns a single pin specified by the pinId
    @GetMapping("/{pinId}")
    public @ResponseBody Optional<Pin> getPinById(@PathVariable Integer pinId) {
        return pinRepository.findById(pinId);
    }

    // http://<hostname>/perfume/pin/<pinId>/users
    // Returns a list of users who own the pin specified by pinId
    @GetMapping("/{pinId}/users")
    public @ResponseBody Set<User> getPinsUsers(@PathVariable Integer pinId) {
        Optional<Pin> pin = pinRepository.findById(pinId);
        Set<User> pinUsers = pin.get().getUsers();
        return pinUsers;
    }

    // POST - http://<hostname>/perfume/pin/add
    // Adds a pin

    // PUT/PATCH - http://<hostname>/perfume/pin/<pinId>
    // Updates a pin specified by pinId

    // DELETE - http://<hostname>/perfume/pin/<pinId>
    // Deletes a pin specified by pinId (Will need to clean up the cross table entries as well)
}
