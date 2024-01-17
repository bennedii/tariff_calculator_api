package ru.fastdelivery.presentation.calc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fastdelivery.domain.common.Volume.Volume;
import ru.fastdelivery.domain.common.coordinates.CoordinatesGroup;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.domain.delivery.shipment.VolumeShipment;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.request.CargoPackage;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.TariffCoordinatesUseCase;

@RestController
@RequestMapping("/api/v1/calculate/")
@RequiredArgsConstructor
@Tag(name = "Расчеты стоимости доставки")
public class CalculateController {
    private final TariffCalculateUseCase tariffCalculateUseCase;
    private final CurrencyFactory currencyFactory;
    private final TariffCoordinatesUseCase tariffCoordinatesUseCase;

    @PostMapping
    @Operation(summary = "Расчет стоимости по упаковкам груза")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    public CalculatePackagesResponse calculate(
            @Valid @RequestBody CalculatePackagesRequest request) {


        var packsWeights = request.packages().stream()
                .map(CargoPackage::weight)
                .map(Weight::new)
                .map(Pack::new)
                .toList();
        
        var packsVolumes = request.packages().stream()
                .map(CargoPackage::outerDimensions)
                .map(Volume::getVolume)
                .toList();

        var coordinates = tariffCoordinatesUseCase.checkCoordinates(CoordinatesGroup.getCoordinatesGroup(request.getCoordinates()));

        var weightshipment = new Shipment(packsWeights, currencyFactory.create(request.currencyCode()));
        var volumeShipment = new VolumeShipment(packsVolumes, currencyFactory.create(request.currencyCode()));


        var calculatedWeightPrice = tariffCalculateUseCase.calc(weightshipment);
        var calculatedVolumePrice = tariffCalculateUseCase.calcPriceWithVolume(volumeShipment);
        var finalVar = calculatedWeightPrice.getMostBiggerPrice(calculatedVolumePrice);
        var minimalPrice = tariffCalculateUseCase.minimalPrice();



        var calculatedPriceWithDistance = tariffCoordinatesUseCase.calc(finalVar, coordinates);


        return new CalculatePackagesResponse(calculatedPriceWithDistance, minimalPrice);
    }
}

