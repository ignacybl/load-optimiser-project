package pl.ignacy.reactiveproducer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
@RequiredArgsConstructor
public class ReactiveLoadingPlanProducer {

    private final KafkaSender<String, Object> kafkaSender;

    public Mono<Void> send(LoadingPlanRequest request) {
        SenderRecord<String, Object, String> record = SenderRecord.create(
                new ProducerRecord<>("loading-plan-requests", request),
                null
        );

        return kafkaSender
                .send(Mono.just(record))
                .then();
    }
}
