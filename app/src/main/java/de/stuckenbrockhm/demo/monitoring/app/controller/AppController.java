package de.stuckenbrockhm.demo.monitoring.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import de.stuckenbrockhm.demo.monitoring.api.BillingBaseApi;
import de.stuckenbrockhm.demo.monitoring.api.ContentBaseApi;
import de.stuckenbrockhm.demo.monitoring.api.ContentFullApi;
import de.stuckenbrockhm.demo.monitoring.api.Drink;
import lombok.extern.java.Log;

@Log
@RestController
public class AppController {

    @Value("${demo-monitoring-app.service.auth.url}")
    private String authServiceHost;

    @Value("${demo-monitoring-app.service.billing.url}")
    private String billingServiceHost;

    @Value("${demo-monitoring-app.service.content.url}")
    private String contentServiceHost;

    private RestTemplate rest;

    @Autowired
    public AppController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping(path = "/content", produces = "application/json")
    public ResponseEntity<ContentBaseApi> save() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        BillingBaseApi billing = getSubscription(username);

        ContentBaseApi content = getContents();

        ArrayList<Drink> drinks = new ArrayList<>();
        for(Drink drink : content.getDrinks()){
            if(drink.getMinSubscriptionLevel().getValue() <= billing.getSubscription().getValue()){
                drinks.add(drink);
            }

            if(drinks.size() >= 5){
                break;
            }
        }

        return new ResponseEntity<>(new ContentFullApi(drinks.toArray(new Drink[drinks.size()])), HttpStatus.OK);
    }

    private BillingBaseApi getSubscription(String username) {
        return rest.getForObject(billingServiceHost + "/subscriptions/" + username, BillingBaseApi.class);
    }

    private ContentBaseApi getContents() {
        return rest.getForObject(contentServiceHost + "/contents", ContentBaseApi.class);
    }
}
