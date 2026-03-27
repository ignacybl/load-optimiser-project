package pl.ignacy.load_optimiser_common.dto;

import pl.ignacy.load_optimiser_common.enums.StrategyType;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LoadingPlanRequest(@NotEmpty(message = "No vehicles") List<Long> vehicleIds, @NotEmpty(message = "No packages") List<Long> packagesIds, StrategyType strategyType) {
}
