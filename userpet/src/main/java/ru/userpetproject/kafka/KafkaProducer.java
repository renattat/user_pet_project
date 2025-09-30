package ru.userpetproject.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;

    public void sendEventToKafka(String topic, Long key, String payload) {
        try {
            CompletableFuture<SendResult<Long, String>> sendResult = kafkaTemplate.send(topic, key, payload);
            SendResult<Long, String> result = sendResult.get();
            log.info("Sending message to kafka: {}", result.toString());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error sending event to Kafka: {}", e.getMessage());
        }
    }
}
