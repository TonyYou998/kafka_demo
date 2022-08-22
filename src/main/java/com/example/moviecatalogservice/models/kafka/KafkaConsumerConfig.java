package com.example.moviecatalogservice.models.kafka;

import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.kafka.common.ApplicationConstant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, Movie> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
        JsonDeserializer<Movie> jsonDeserializer=new JsonDeserializer<>(Movie.class,false);
        jsonDeserializer.addTrustedPackages("*");

//        props.put(
//                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                "localhost:9092");
//        props.put(
//                ConsumerConfig.GROUP_ID_CONFIG,
//                "movie");
//        props.put(
//                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
//        props.put(
//                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Movie>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Movie> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;

    }
    @Bean
    public Map<String, Object> consumerConfigs() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConstant.KAFKA_LOCAL_SERVER_CONFIG,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                ConsumerConfig.GROUP_ID_CONFIG, "movie"
        );
    }
}