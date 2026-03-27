package pl.ignacy.reactiveproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class ReactiveLoadingPlanController {

    private final ReactiveLoadingPlanProducer producer;

    @PostMapping
    public Mono<Void> createPlan(@RequestBody LoadingPlanRequest request) {
        return producer.send(request);
    }
}
