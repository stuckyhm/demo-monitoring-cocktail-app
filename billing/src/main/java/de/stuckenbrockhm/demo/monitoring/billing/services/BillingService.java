package de.stuckenbrockhm.demo.monitoring.billing.services;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import de.stuckenbrockhm.demo.monitoring.api.BillingBaseApi;
import de.stuckenbrockhm.demo.monitoring.api.BillingFullApi;
import de.stuckenbrockhm.demo.monitoring.api.SubscriptionLevel;

@Service
public class BillingService {

    static final String SUBSCRIPTION_COUNTER_NAME = "demo-monitoring-billing.subscription.counter";

    public BillingBaseApi userSubscriptions(String username) throws NoSuchAlgorithmException {

        if(username == null){
            return new BillingFullApi(SubscriptionLevel.NO_SUBSCRIPTION);
        }else if(username.charAt(0) % 7 == 0){
            return new BillingFullApi(SubscriptionLevel.PLATIN);
        }else if(username.charAt(0) % 5 == 0){
            return new BillingFullApi(SubscriptionLevel.PREMIUM);
        }else if(username.charAt(0) % 3 == 0){
            return new BillingFullApi(SubscriptionLevel.STANDARD);
        }else{
            return new BillingFullApi(SubscriptionLevel.NO_SUBSCRIPTION);
        }
    }

}
