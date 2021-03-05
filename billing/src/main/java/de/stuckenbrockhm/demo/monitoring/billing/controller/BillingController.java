package de.stuckenbrockhm.demo.monitoring.billing.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.stuckenbrockhm.demo.monitoring.api.BillingBaseApi;
import de.stuckenbrockhm.demo.monitoring.billing.services.BillingService;

import lombok.extern.java.Log;

@Log
@RestController
public class BillingController {

    private BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping(path = "/subscriptions/{username}", produces = "application/json")
    public ResponseEntity<BillingBaseApi> save(@PathVariable(name = "username") String username) throws NoSuchAlgorithmException {

        BillingBaseApi billing = billingService.userSubscriptions(username);

        return new ResponseEntity<>(billing, HttpStatus.OK);
    }

}