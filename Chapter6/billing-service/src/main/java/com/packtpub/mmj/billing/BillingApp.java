package com.packtpub.mmj.billing;

import com.packtpub.mmj.billing.domain.message.BillingMessageChannels;
import com.packtpub.mmj.billing.domain.message.EventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Sourabh Sharma
 */
@SpringBootApplication(scanBasePackages = {"com.packtpub.mmj.billing", "com.packtpub.mmj.booking"})
@EnableBinding({BillingMessageChannels.class})
public class BillingApp {

    public static void main(String[] args) {
        SpringApplication.run(BillingApp.class, args);
    }

    @Bean
    public EventListener eventListener() {
        return new EventListener();
    }
}
