package com.fiuza.order_notification.infra.queue.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "consultation_exchange";
    public static final String APPOINTMENT_EVENT_QUEUE = "appointment.event.queue";
    public static final String APPOINTMENT_CANCELED_QUEUE = "appointment.canceled.queue";
    public static final String APPOINTMENT_UPDATED_QUEUE = "appointment.updated.queue";
    public static final String ROUTING_KEY_NEW = "reservation.new";
    public static final String ROUTING_UPDATED = "reservation.updated";
    public static final String ROUTING_CANCELED = "reservation.canceled";



    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appointmentEventQueue() {
        return new Queue(APPOINTMENT_EVENT_QUEUE, true);
    }

    @Bean
    public Queue appointmentCanceledQueue() {
        return new Queue(APPOINTMENT_CANCELED_QUEUE, true);
    }

    @Bean
    public Queue appointmentUpdatedQueue() {
        return new Queue(APPOINTMENT_UPDATED_QUEUE, true);
    }


    @Bean
    public Binding appointmentBinding(Queue appointmentEventQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(appointmentEventQueue).to(directExchange).with(ROUTING_KEY_NEW);
    }

    @Bean
    public Binding appointmentCanceledBinding(Queue appointmentCanceledQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(appointmentCanceledQueue).to(directExchange).with(ROUTING_CANCELED);
    }

    @Bean
    public Binding appointmentUpdatedBinding(Queue appointmentUpdatedQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(appointmentUpdatedQueue).to(directExchange).with(ROUTING_UPDATED);
    }


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
