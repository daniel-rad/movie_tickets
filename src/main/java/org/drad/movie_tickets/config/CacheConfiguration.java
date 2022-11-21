package org.drad.movie_tickets.config;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.log4j.Log4j2;
import org.drad.movie_tickets.cache.Caches;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Log4j2
public class CacheConfiguration {

//    @Bean(name = "cacheManager")
//    public CacheManager cacheManager() {
//        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
//              .initialCapacity(100)
//              .maximumSize(500)
//              .expireAfterAccess(10, TimeUnit.MINUTES)
//              .recordStats();
//
//        CaffeineCache caffeineCache = new CaffeineCache("caffeineCache", caffeineBuilder.build());
//        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//        simpleCacheManager.setCaches(Arrays.stream(Caches.getCacheNames()).map(cacheName -> new CaffeineCache("caffeineCache", caffeineBuilder.build())).collect(
//              Collectors.toList()));
//        return simpleCacheManager;
//    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(Caches.getCacheNames()) {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                Caches cache = Caches.getCacheByName(name);
                log.debug("Setting up cache {} for {}.", cache.getName(), cache.getDuration());
                ConcurrentMap<Object, Object> store = CacheBuilder.newBuilder()
                      .expireAfterWrite(cache.getDuration())
                      .build()
                      .asMap();
                return new ConcurrentMapCache(name, store, true);
            }
        };
    }
}
