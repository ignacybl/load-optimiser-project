package pl.ignacy.producer_load_optimiser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;
import pl.ignacy.producer_load_optimiser.outbox.OutboxMessage;
import pl.ignacy.producer_load_optimiser.outbox.OutboxRepository;

@Component
@RequiredArgsConstructor
public class LoadingPlanProducer {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void send(LoadingPlanRequest request) {
        String payload = objectMapper.writeValueAsString(request);
        outboxRepository.save(new OutboxMessage("loading-plan-requests", payload));
    }
}
