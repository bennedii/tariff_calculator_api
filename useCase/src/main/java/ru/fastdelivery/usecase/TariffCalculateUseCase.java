package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.coordinates.Coordinates;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.domain.delivery.shipment.VolumeShipment;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;
    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();

        return weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg)
                .max(minimalPrice);
    }
    public Price calcPriceWithVolume(VolumeShipment shipment){
        var volumeAllPackages = shipment.volumeAllPackages();
        var minimalPrice = weightPriceProvider.minimalPrice();

        return weightPriceProvider
                .costPerCubicMeter()
                .multiply(volumeAllPackages)
                .max(minimalPrice);
    }



    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
