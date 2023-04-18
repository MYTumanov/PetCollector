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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.petcollector.petcollector.api.service.IDebtService;
import ru.petcollector.petcollector.exception.EntityNotFoundException;
import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDTO;

@RestController
@RequestMapping(path = "api/debt")
public class DebtApi {

    @NotNull
    @Autowired
    private IDebtService service;

    @GetMapping("/{id}")
    public ResponseEntity<Debt> getDebt(@PathVariable @NotNull final String id,
            @RequestParam("userId") @NotNull final String userId) {
        try {
            @NotNull
            final Debt debt = service.findByIdAndUserId(id, userId);
            return ResponseEntity.ok(debt);
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AggregateDebt>> getDebtList(@RequestParam("userId") @NotNull final String userId) {
        try {
            return ResponseEntity.ok(service.findAllByUserId(userId));
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Debt> createDebt(@RequestBody @Nullable final DebtDTO debtDTO,
            @RequestParam("userId") @NotNull final String userId) {
        try {
            return ResponseEntity.ok(service.create(debtDTO, userId));
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Debt> updateDebt(@PathVariable @NotNull final String id,
            @RequestBody @Nullable final DebtDTO debtDTO,
            @RequestParam("userId") @NotNull final String userId) {
        try {
            return ResponseEntity.ok(service.updateByIdAndUserId(debtDTO, id, userId));
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Debt> deleteDebt(@PathVariable @NotNull final String id,
            @RequestParam("userId") @NotNull final String userId) {
        try {
            service.deleteByIdAndUserId(id, userId);
            return ResponseEntity.ok().build();
        } catch (@NotNull final EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (@NotNull final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
