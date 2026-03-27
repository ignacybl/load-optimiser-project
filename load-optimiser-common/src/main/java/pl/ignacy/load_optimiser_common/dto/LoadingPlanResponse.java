package pl.ignacy.load_optimiser_common.dto;

import pl.ignacy.load_optimiser_common.enums.VehicleType;

import java.time.LocalDateTime;
import java.util.List;

public record LoadingPlanResponse(Long id, String plateNumber,
                                  VehicleType vehicleType, List<PackageResponse> packages,
                                  double totalWeight, double totalVolume, double weightFillPercentage, double volumeFillPercentage, LocalDateTime createdAt) {
}
