package com.services.reservations.Configurations;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConsumerConfiguration {

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public Queue reservationQueue() {
        return new Queue("reservation.queue");
    }

}
