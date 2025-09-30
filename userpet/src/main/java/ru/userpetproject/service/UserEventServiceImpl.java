package ru.userpetproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.userpetproject.entity.User;
import ru.userpetproject.entity.UserEvent;
import ru.userpetproject.enums.UserStatus;
import ru.userpetproject.exception.OrderProcessingException;
import ru.userpetproject.repository.UserEventRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventServiceImpl implements UserEventService {

    private final UserEventRepository userEventRepository;
    private final KafkaService kafkaService;
    private final ObjectMapper objectMapper;

    @Override
    @Scheduled(fixedRateString = "${configuration.kafka.scheduled}")
    public void eventProcessing() {
        List<UserEvent> listOfUserEvents = new ArrayList<>();
        listOfUserEvents.addAll(userEventRepository.findAll());
        log.info("Number of user events: {}", listOfUserEvents.size());

        if (!listOfUserEvents.isEmpty()) {
            for (UserEvent userEvent : listOfUserEvents) {
                log.info("Sending event to Kafka");
                kafkaService.sendEventToKafka(userEvent.getId(), userEvent);
                userEventRepository.deleteById(userEvent.getId());
            }
        }
    }

    @Override
    public void saveUserEvent(User userEntity, UserStatus userStatus) {
        UserEvent userEvent = new UserEvent();
        try {
            userEvent.setEventPayload(objectMapper.writeValueAsString(userEntity));
            userEvent.setUserStatus(userStatus);
            userEventRepository.save(userEvent);
        } catch (JsonProcessingException e) {
            throw new OrderProcessingException("Error processing user event creation", e.getMessage());
        }
    }


}
