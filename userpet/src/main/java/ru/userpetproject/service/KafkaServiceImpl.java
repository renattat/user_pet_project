package ru.userpetproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.userpetproject.entity.UserEvent;
import ru.userpetproject.kafka.KafkaProducer;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @Value("${configuration.kafka.user-out-topic}")
    private String userOutTopic;

    @Override
    public void sendEventToKafka(Long key, UserEvent userEvent) {
        try {
            String payload = objectMapper.writeValueAsString(userEvent);
            kafkaProducer.sendEventToKafka(userOutTopic, key, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("ошибка при парсинге в строку: " + userEvent);
        }
    }
}

