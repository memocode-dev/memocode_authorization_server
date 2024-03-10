package dev.memocode.memocode_authorization_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        ports = {9092}
)
@SpringBootTest
@ActiveProfiles("test")
class MemocodeAuthorizationServerApplicationTests {

    @Test
    void contextLoads() {
    }

}
