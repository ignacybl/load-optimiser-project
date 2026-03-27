package pl.ignacy.load_optimiser_common.dto;
import pl.ignacy.load_optimiser_common.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PackageRequest(@Positive double weight, @Positive double volume,
                             @NotNull Priority priority, @NotBlank String deliveryAddress) {
}
