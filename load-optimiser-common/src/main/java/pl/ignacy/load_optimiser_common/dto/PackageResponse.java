package pl.ignacy.load_optimiser_common.dto;

import pl.ignacy.load_optimiser_common.enums.Priority;

public record PackageResponse(Long id, double weight, double volume, Priority priority, String deliveryAddress) {
}
