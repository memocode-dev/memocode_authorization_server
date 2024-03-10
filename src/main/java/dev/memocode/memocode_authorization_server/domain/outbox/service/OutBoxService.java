package dev.memocode.memocode_authorization_server.domain.outbox.service;

import dev.memocode.memocode_authorization_server.domain.outbox.dto.OutBoxCreateDTO;
import dev.memocode.memocode_authorization_server.domain.outbox.entity.OutBox;
import dev.memocode.memocode_authorization_server.domain.outbox.repository.OutBoxRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class OutBoxService {

    private final OutBoxRepository outBoxRepository;

    public OutBox createOutBox(@Valid OutBoxCreateDTO dto) {
        OutBox outbox = OutBox.builder()
                .aggregateId(dto.getAggregateId())
                .aggregateType(dto.getAggregateType())
                .eventType(dto.getEventType())
                .payload(dto.getPayload().toPrettyString())
                .build();

        OutBox outBox = outBoxRepository.save(outbox);

        log.info("created outbox : {}", outBox);

        return outBox;
    }
}
