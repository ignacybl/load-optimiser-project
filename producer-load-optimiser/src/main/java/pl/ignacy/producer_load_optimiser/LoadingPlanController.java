package pl.ignacy.producer_load_optimiser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ignacy.load_optimiser_common.dto.LoadingPlanRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class LoadingPlanController {

    private final LoadingPlanProducer producer;

    @PostMapping
    public void createPlan(@RequestBody LoadingPlanRequest request) {
        producer.send(request);
    }
}