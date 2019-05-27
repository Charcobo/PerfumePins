/**
 * @author Charlie Logan
 * @date May 26, 2019
 */

package com.perfumepins.controller;

import com.perfumepins.model.Pin;
import com.perfumepins.model.User;
import com.perfumepins.repository.PinRepository;
import com.perfumepins.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("app")
public class AppController {

    @Autowired
    PinRepository pinRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String index(Model model) {

        String title = "Perfume Pins App";
        model.addAttribute("title", title);
        return "app/index";
    }

    @GetMapping("users")
    public String users(Model model) {

        List<User> users = userRepository.findAll();
        String title = "Perfume Pins Users";
        model.addAttribute("users", users);
        model.addAttribute("title", title);
        return "app/users";
    }

    @GetMapping("pins")
    public String pins(Model model) {

        List<Pin> pins = pinRepository.findAll();
        String title = "Perfume Pins List";
        model.addAttribute("pins", pins);
        model.addAttribute("title", title);
        return "app/pins";
    }
}
