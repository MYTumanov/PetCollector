package ru.petcollector.petcollector.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.petcollector.petcollector.component.Bootstrap;

@Service
public class AdminService {

    @NotNull
    @Autowired
    private Bootstrap bootstrap;

    public boolean loadData() {
        try {
            bootstrap.initData();
            return true;
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteData() {
        try {
            bootstrap.cleanRepository();
            return true;
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
