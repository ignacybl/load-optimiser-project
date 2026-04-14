package pl.ignacy.producer_load_optimiser.outbox;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OutboxScheduler {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void processOutbox() {
        List<OutboxMessage> pending = outboxRepository.findByStatus(OutboxStatus.PENDING);

        for (OutboxMessage message : pending) {
            try {
                kafkaTemplate.send(message.getTopic(), message.getPayload()).get();
                message.setStatus(OutboxStatus.SENT);
                message.setSentAt(LocalDateTime.now());
                log.info("Sent outbox message id={}", message.getId());
            } catch (Exception e) {
                message.setStatus(OutboxStatus.FAILED);
                log.error("Failed to send outbox message id={}", message.getId(), e);
            }
            outboxRepository.save(message);
        }
    }
}
