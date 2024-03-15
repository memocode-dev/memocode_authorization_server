package dev.memocode.memocode_authorization_server.in.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.memocode.memocode_authorization_server.domain.account.dto.AccountUpdateAfterSignupDTO;
import dev.memocode.memocode_authorization_server.domain.account.mapper.AccountMapper;
import dev.memocode.memocode_authorization_server.in.kafka.event.UserCreatedEvent;
import dev.memocode.memocode_authorization_server.out.outbox.entity.EventType;
import dev.memocode.memocode_authorization_server.usecase.AccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutBoxListener {

    private final ObjectMapper objectMapper;
    private final AccountUseCase accountUseCase;
    private final AccountMapper accountMapper;

    private final static String EVENT_TYPE = "eventType";

    @KafkaListener(topics = {"#{'${spring.kafka.topic.users}'}"}, groupId = "outbox")
    public void listen(ConsumerRecord<String, String> record) throws JsonProcessingException {

        Headers headers = record.headers();

        Optional<Header> _eventTypeHeader = Optional.ofNullable(headers.lastHeader(EVENT_TYPE));

        if (_eventTypeHeader.isEmpty()) {
            return;
        }

        Optional<EventType> _eventType =
                EventType.fromString(new String(_eventTypeHeader.get().value(), StandardCharsets.UTF_8));

        if (_eventType.isEmpty()) {
            return;
        }

        EventType eventType = _eventType.get();

        switch (eventType) {
            case USER_CREATED -> {
                UserCreatedEvent userCreatedEvent = objectMapper.readValue(record.value(), UserCreatedEvent.class);

                AccountUpdateAfterSignupDTO accountUpdateAfterSignupDTO =
                        accountMapper.userCreatedEvent_to_accountUpdateDTO(userCreatedEvent);

                accountUseCase.updateAccountAfterSignup(accountUpdateAfterSignupDTO);
            }
        }
    }
}
