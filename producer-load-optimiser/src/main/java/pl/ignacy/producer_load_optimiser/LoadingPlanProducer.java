package pl.ignacy.producer_load_optimiser;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;

@Component
@RequiredArgsConstructor
public class LoadingPlanProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(LoadingPlanRequest request){
        kafkaTemplate.send("loading-plan-requests", request);
    }
}
