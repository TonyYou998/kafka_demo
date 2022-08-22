package com.example.moviecatalogservice.models.kafka;


import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.kafka.common.ApplicationConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class KafkaConsumer {
        private final static Logger LOGGER= LoggerFactory.getLogger(KafkaConsumer.class);

//        @KafkaListener(topics = ApplicationConstant.TOPIC_NAME,groupId = ApplicationConstant.GROUP_ID_JSON)
//        public  void consume( Movie data) throws JsonProcessingException {
////                ObjectMapper mapper=new ObjectMapper();
////                String jsonString =mapper.writeValueAsString(data);
//                LOGGER.info(String.format("receive data ->%S",data));
//        }
}
