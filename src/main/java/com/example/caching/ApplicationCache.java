package com.example.caching;

import com.example.caching.model.CachedResponse;
import com.example.twitter_api.models.message.TimeLineResponse;
import lombok.extern.slf4j.Slf4j;
import org.cacheonix.Cacheonix;
import org.cacheonix.cache.Cache;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
public class ApplicationCache {

    private static final long cacheTimeout = 60000;
    private static final String defaultFilter = "timeLineResponse";
    private Cacheonix cacheonix;
    private Cache<String, CachedResponse> cache;

    public ApplicationCache() {
        cacheonix = Cacheonix.getInstance();
        cache = cacheonix.getCache("application.cache");
        log.info("Application cache initialized");
    }

    public Optional<CachedResponse> getResponseFromCache(String filter) {
        if (filter.length() == 0) {
            log.info("No filter provied, setting to default filter calue : {}", defaultFilter);
            filter = defaultFilter;
        }
        Optional<CachedResponse> timeLineResponseOptional = Optional.empty();
        Instant now = Instant.now();
        if (cache.containsKey(filter)) {
            CachedResponse cachedResponse = cache.get(filter);
            long timeDiff = Duration.between(cachedResponse.getLastModified(), now).toMillis();
            if (timeDiff > cacheTimeout) {
                log.debug("Cache timed out");
                return timeLineResponseOptional;
            }
            timeLineResponseOptional = Optional.of(cache.get(filter));
        }
        return timeLineResponseOptional;
    }

    public void updateCache(String filter, TimeLineResponse timeLineResponse) {
        Instant now = Instant.now();
        log.info("Updating cache contents");
        if (filter.length() == 0) {
            log.info("No filter provied, setting to default filter calue : {}", defaultFilter);
            filter = defaultFilter;
        }
        CachedResponse cachedResponse = new CachedResponse();
        if (cache.containsKey(filter)) {
            log.debug("Key present in cache : {}", filter);
            CachedResponse lineResponseInCache = cache.get(filter);
            long timeDiff = Duration.between(lineResponseInCache.getLastModified(), now ).toMillis();
            if (timeDiff > cacheTimeout) {
                log.debug("Cache timed out, refresing cache with new contents, key = {}", filter);
                cache.remove(filter);
                cachedResponse.setLastModified(now);
                cachedResponse.setTimeLineResponse(timeLineResponse);
                cache.put(filter, cachedResponse);
            }
        } else {
            log.debug("Putting content in cache with key = {}", filter);
            cachedResponse.setTimeLineResponse(timeLineResponse);
            cachedResponse.setLastModified(now);
            cache.put(filter, cachedResponse);
        }
    }
}
