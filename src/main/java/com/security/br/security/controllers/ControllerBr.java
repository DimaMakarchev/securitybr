package com.security.br.security.controllers;

import com.security.br.security.entities.User;
import com.security.br.security.services.ServiceBr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ControllerBr {

    @Autowired
    private ServiceBr serviceBr;


    @RequestMapping("/")
    public String methodSimoleInter() {
        return "home bro";
    }

    @RequestMapping("/bro")
    public String methodAutBro(Principal principal) {
        User byUsername = serviceBr.findByUsername(principal.getName());

        return byUsername.getUsername();
    }

    @RequestMapping("/ti")
    public String methodAutTi(Principal principal) {
        User byUsername = serviceBr.findByUsername(principal.getName());
        return "ti" + byUsername.getUsername();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile() {
        return "read profile page";
    }

    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins() {
        return "admins page";
    }

}
