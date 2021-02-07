package com.ua.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail-props.properties")
@RequiredArgsConstructor
public class MailConfig {

    private final Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(587);
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));

        val properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", environment.getProperty("spring.mail.protocol"));
        properties.setProperty("mail.debug", environment.getProperty("spring.mail.debug"));
        properties.setProperty("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

        return mailSender;
    }
}
