package pl.ignacy.load_optimiser_common.dto;

import pl.ignacy.load_optimiser_common.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record VehicleRequest(@NotBlank @Pattern(regexp = "^[A-Z]{1,3}[A-Z0-9]{4,5}$", message = "invalid plate number") String plateNumber,
                             @Positive double maxWeight, @Positive double maxVolume, @NotNull VehicleType vehicleType) {
}
