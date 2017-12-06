package com.packtpub.mmj.booking;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@SpringBootApplication
public class BookingApp {

    private static final Logger LOG = LoggerFactory.getLogger(BookingApp.class);

    public static void main(String[] args) {
        SpringApplication.run(BookingApp.class, args);
    }

    @Bean
    public MessageConverter bookingOrderMessageConverter() throws IOException {
        LOG.info("avro message converter bean initialized.");
        AvroSchemaMessageConverter avroSchemaMessageConverter = new AvroSchemaMessageConverter(MimeType.valueOf("application/bookingOrder.v1+avro"));
        avroSchemaMessageConverter.setSchemaLocation(new ClassPathResource("avro/bookingOrder.avsc"));
        return avroSchemaMessageConverter;
    }
}
