/**
 * @author Charlie Logan
 * @date May 26, 2019
 */

package com.perfumepins.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Size(min = 3)
    @Column(unique = true)
    private String username;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "user_x_pin",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "pin_id") }
    )
    private List<Pin> pins;

    public void addPin(Pin pin) { pins.add(pin); }

    public void addPins(ArrayList<Pin> pins) { pins.addAll(pins); }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<Pin> getPins() { return pins; }

    public void setUsername(String username) {
        this.username = username;
    }
}
