package org.drad.movie_tickets.cache;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * This class contains the cache configurations.
 */
public enum Caches {

    TICKETS(Caches.CACHE_TICKETS, Duration.ofDays(1)),
    GROUP_DISCOUNTS(Caches.CACHE_GROUP_DISCOUNTS, Duration.ofDays(1));


    final String name;
    final Duration duration;

    public static final String CACHE_TICKETS = "tickets";
    public static final String CACHE_GROUP_DISCOUNTS = "group_discounts";

    Caches(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public static String[] getCacheNames() {
        return Stream.of(Caches.values())
              .map(Caches::getName)
              .toArray(String[]::new);
    }

    public static Caches getCacheByName(String cacheName) {
        return Stream.of(Caches.values())
              .filter(cache -> cache.name.equalsIgnoreCase(cacheName))
              .findFirst()
              .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid cache name %s", cacheName)));
    }
}
