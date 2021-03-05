package de.stuckenbrockhm.demo.monitoring.content.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.stuckenbrockhm.demo.monitoring.api.ContentBaseApi;
import de.stuckenbrockhm.demo.monitoring.api.ContentFullApi;
import de.stuckenbrockhm.demo.monitoring.api.Drink;
import de.stuckenbrockhm.demo.monitoring.api.SubscriptionLevel;
import de.stuckenbrockhm.demo.monitoring.content.beans.Cocktail;
import de.stuckenbrockhm.demo.monitoring.content.beans.Cocktails;

@Service
public class ContentService {

    private RestTemplate rest;

    @Value("${demo-monitoring-content.ext-service.cocktaildb.url}")
    private String cocktaildbServiceHost;

    @Autowired
    public ContentService(RestTemplate rest) {
        this.rest = rest;
    }

    public ContentBaseApi getContents() {
        ArrayList<Drink> drinks = new ArrayList<>();

        Cocktails cocktails = rest.getForObject(cocktaildbServiceHost, Cocktails.class);

        if(cocktails != null){
            for(Cocktail cocktail : cocktails.drinks){
                drinks.add(new Drink(cocktail.getIdDrink(),
                    cocktail.getStrDrinkThumb(),
                    cocktail.getStrDrink(),
                    calcSubscriptionLevel(cocktail)));
            }
        }

        return new ContentFullApi(drinks.toArray(new Drink[drinks.size()]));
    }

    private SubscriptionLevel calcSubscriptionLevel(Cocktail cocktail){
        SubscriptionLevel level;
        if(cocktail.getStrDrink().charAt(0) % 5 == 0){
            level = SubscriptionLevel.PLATIN;
        }else if(cocktail.getStrDrink().charAt(0) % 3 == 0){
            level = SubscriptionLevel.PREMIUM;
        }else{
            level = SubscriptionLevel.STANDARD;
        }

        return level;
    }
}
