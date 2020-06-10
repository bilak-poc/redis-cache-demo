package com.github.bilakpoc.rediscachedemo.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class CacheConfig {

  private final ObjectMapper objectMapper;

  public CacheConfig(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Bean
  public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
    return builder -> builder.cacheDefaults(defaultCacheConfig(Duration.ofMinutes(2)));
  }

  private RedisCacheConfiguration defaultCacheConfig(Duration ttl) {
    return RedisCacheConfiguration.defaultCacheConfig()
      .disableCachingNullValues()
      .entryTtl(ttl)
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
        new GenericJackson2JsonRedisSerializer(objectMapper)));
  }
}
