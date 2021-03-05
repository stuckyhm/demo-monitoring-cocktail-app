package de.stuckenbrockhm.demo.monitoring.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Drink {

    private String id;
    private String name;
    private String imageUrl;
    private SubscriptionLevel minSubscriptionLevel;
}
