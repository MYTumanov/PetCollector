package ru.petcollector.petcollector.api.controller;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.Debt;
import ru.petcollector.petcollector.model.DebtDTO;
import ru.petcollector.petcollector.service.DebtService;

@RestController
@RequestMapping(path = "api/debt")
public class DebtApi {

    @NotNull
    @Autowired
    private DebtService service;

    @GetMapping("/{id}")
    public ResponseEntity<Debt> getDebt(@PathVariable String id) {
        try {
            @Nullable
            final Debt debt = service.findById(id);
            return ResponseEntity.ok(debt);
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Debt>> getDebtList() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Debt> createDebt(@RequestBody DebtDTO debtDTO) {
        try {
            return ResponseEntity.ok(service.create(debtDTO));
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Debt> updateDebt(@RequestBody DebtDTO debtDTO) {
        try {
            return ResponseEntity.ok(service.update(debtDTO));
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Debt> deleteDebt(@PathVariable String id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
