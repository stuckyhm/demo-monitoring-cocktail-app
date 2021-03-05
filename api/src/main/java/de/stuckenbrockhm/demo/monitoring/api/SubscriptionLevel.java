package de.stuckenbrockhm.demo.monitoring.api;

import java.util.HashMap;
import java.util.Map;

public enum SubscriptionLevel {

    NO_SUBSCRIPTION(0),
    STANDARD(1),
    PREMIUM(2),
    PLATIN(3);

    private int levelNo;

    private static Map<Integer, SubscriptionLevel> map = new HashMap<Integer, SubscriptionLevel>();

    static {
        for (SubscriptionLevel legEnum : SubscriptionLevel.values()) {
            map.put(legEnum.levelNo, legEnum);
        }
    }

    private SubscriptionLevel(final int level) {
        levelNo = level;
    }
    public int getValue() {
        return levelNo;
    }

    public static SubscriptionLevel valueOf(int level) {
        return map.get(level);
    }
}