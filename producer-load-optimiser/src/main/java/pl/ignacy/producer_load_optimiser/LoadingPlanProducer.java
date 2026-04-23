package pl.ignacy.producer_load_optimiser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;
import pl.ignacy.producer_load_optimiser.outbox.OutboxMessage;
import pl.ignacy.producer_load_optimiser.outbox.OutboxRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoadingPlanProducer {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void send(LoadingPlanRequest request) {
        try {
            String payload = objectMapper.writeValueAsString(request);
            String requestId = UUID.randomUUID().toString();
            outboxRepository.save(new OutboxMessage("loading-plan-requests", requestId, payload));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize request", e);
        }
    }
}
