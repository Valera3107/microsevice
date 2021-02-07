package com.ua.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.ua.config.RabbitMQConfig.QUEUE_DEPOSIT;

@Service
@RequiredArgsConstructor
public class DepositMessageHandler {

    private final JavaMailSender mailSender;

    @RabbitListener(queues = QUEUE_DEPOSIT)
    public void receiver(Message message) throws JsonProcessingException {
        val body = message.getBody();
        val jsonBody = new String(body);
        val mapper = new ObjectMapper();
        val depositResponseDTO = mapper.readValue(jsonBody, DepositResponseDTO.class);

        val mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponseDTO.getEmail());
        mailMessage.setFrom("bad@boy.com");
        mailMessage.setSubject("Deposit");
        mailMessage.setText("Make deposit, sum: " + depositResponseDTO.getAmount());

        try {
            mailSender.send(mailMessage);
        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }
    }
}
