package com.example.medicinecache.alert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AlertConfig {

    public static final String DEFAULT_TOPIC = "pharmacy-alerts";

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            AlertSubscriber subscriber) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        MessageListenerAdapter listener = new MessageListenerAdapter(subscriber, "receive");
        listener.setSerializer(new StringRedisSerializer());
        container.addMessageListener(listener, new ChannelTopic(DEFAULT_TOPIC));
        return container;
    }
}
