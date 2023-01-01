package ru.petcollector.petcollector.api;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.model.User;
import ru.petcollector.petcollector.service.UserService;

@RestController
@RequestMapping(path = "api/user")
public class UserApi {

    @NotNull
    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        System.out.println("GET by id");
        return service.findById(id);
    }

    @GetMapping
    public List<User> getUserList() {
        System.out.println("GET all");
        return service.findAll();
    }

}
