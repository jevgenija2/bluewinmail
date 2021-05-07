package com.example.bluewinmailhometask;

import com.example.bluewinmailhometask.model.QuotaUnit;
import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BluewinmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluewinmailApplication.class, args);
    }

    @Bean
    CommandLineRunner init (UserRepository userRe){ //Setting mock user data.
        return args -> {
            UserEntity userEntity1 = new UserEntity();
            userEntity1.setName("Sam");
            userEntity1.setSurname("Wright");
            userEntity1.setEmailAddress("samuel.wright@testMail.com");
            userEntity1.setSubscriptionMonthlyPayment(0.00);
            userEntity1.setSubscriptionPaymentCurrency("$");
            userEntity1.setEmailQuota(QuotaUnit.UP_TO_1GB.getValue());

            UserEntity userEntity2 = new UserEntity();
            userEntity2.setName("John");
            userEntity2.setSurname("Walker");
            userEntity2.setEmailAddress("john.walker@testMail.com");
            userEntity2.setSubscriptionMonthlyPayment(1.00);
            userEntity2.setSubscriptionPaymentCurrency("$");
            userEntity2.setEmailQuota(QuotaUnit.UP_TO_10GB.getValue());

            userRe.save(userEntity1);
            userRe.save(userEntity2);
        };
    }

}
