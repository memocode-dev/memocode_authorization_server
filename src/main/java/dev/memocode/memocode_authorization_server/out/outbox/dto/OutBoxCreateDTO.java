package dev.memocode.memocode_authorization_server.out.outbox.dto;

import com.fasterxml.jackson.databind.JsonNode;
import dev.memocode.memocode_authorization_server.out.outbox.entity.AggregateType;
import dev.memocode.memocode_authorization_server.out.outbox.entity.EventType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutBoxCreateDTO {

    @NotNull(message = "AGGREGATE_ID_NOT_NULL:AggregateId must not be null")
    private UUID aggregateId;

    @NotNull(message = "AGGREGATE_TYPE_NOT_NULL:AggregateType must not be null")
    private AggregateType aggregateType;

    @NotNull(message = "EVENT_TYPE_NOT_NULL:eventType must not be null")
    private EventType eventType;

    @NotNull(message = "PAYLOAD_NOT_NULL:payload must not be null")
    private JsonNode payload;
}
