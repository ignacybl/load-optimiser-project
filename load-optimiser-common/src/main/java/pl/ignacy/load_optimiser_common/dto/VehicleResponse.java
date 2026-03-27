package pl.ignacy.load_optimiser_common.dto;


import pl.ignacy.load_optimiser_common.enums.VehicleType;

public record VehicleResponse(Long id, String plateNumber, VehicleType vehicleType) {
}
