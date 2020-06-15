package com.github.bilakpoc.rediscachedemo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.github.bilakpoc.rediscachedemo.generated.model.ModelImport;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

  private final ObjectMapper objectMapper;

  public CacheConfig(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

//    @Bean
//    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//        return builder -> builder.cacheDefaults(defaultCacheConfig(Duration.ofMinutes(2)));
//    }
//
//    private RedisCacheConfiguration defaultCacheConfig(Duration ttl) {
//        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
//                .builder()
//                .allowIfBaseType(ModelImport.class)
//                .allowIfSubType(ModelImport.class)
//                .build();
//        objectMapper.activateDefaultTyping(ptv);
//        var genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
//
//        var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//
////        var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(ModelImport.class);
////        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .entryTtl(ttl)
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
////                        genericJackson2JsonRedisSerializer
//                        jackson2JsonRedisSerializer
//                ));
//    }
}
