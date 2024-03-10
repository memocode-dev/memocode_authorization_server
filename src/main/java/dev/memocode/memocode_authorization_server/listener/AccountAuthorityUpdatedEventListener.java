package dev.memocode.memocode_authorization_server.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.memocode.memocode_authorization_server.domain.outbox.dto.OutBoxCreateDTO;
import dev.memocode.memocode_authorization_server.domain.outbox.entity.OutBox;
import dev.memocode.memocode_authorization_server.domain.outbox.service.OutBoxService;
import dev.memocode.memocode_authorization_server.kafka.dto.UserCreatedEvent;
import dev.memocode.memocode_authorization_server.usecase.event.AccountAuthorityUpdatedEvent;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import static dev.memocode.memocode_authorization_server.domain.outbox.entity.AggregateType.ACCOUNT;
import static dev.memocode.memocode_authorization_server.domain.outbox.entity.EventType.ACCOUNT_AUTHORITY_UPDATED;

@Slf4j
@Component
@Validated
@Transactional
@RequiredArgsConstructor
public class AccountAuthorityUpdatedEventListener {

    private final OutBoxService outboxService;
    private final ObjectMapper objectMapper;

    @EventListener
    public void onAccountAuthorityUpdated(@Valid AccountAuthorityUpdatedEvent event) {

        OutBoxCreateDTO outBoxCreateDTO = OutBoxCreateDTO.builder()
                .aggregateId(event.getId())
                .aggregateType(ACCOUNT)
                .eventType(ACCOUNT_AUTHORITY_UPDATED)
                .payload(objectMapper.valueToTree(event))
                .build();

        OutBox outBox = outboxService.createOutBox(outBoxCreateDTO);
        log.info("UserCreatedEvent to outBox and outBox saved : {}", outBox);
    }
}
