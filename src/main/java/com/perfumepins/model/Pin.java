/**
 * @author Charlie Logan
 * @date May 26, 2019
 */

package com.perfumepins.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Pin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pinId;
    private String pinName;
    private String pinNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "pins")
    private Set<User> users;

    public Integer getPinId() {
        return pinId;
    }

    public String getPinName() {
        return pinName;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public Set<User> getUsers() { return users; }

    public void setPinName(String pinName) {
        this.pinName = pinName;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public void setUsers(Set<User> users) { this.users = users; }
}
