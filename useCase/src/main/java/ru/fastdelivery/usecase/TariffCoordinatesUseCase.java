package ru.fastdelivery.usecase;

import lombok.AllArgsConstructor;
import ru.fastdelivery.domain.common.coordinates.CoordinatesGroup;
import ru.fastdelivery.domain.common.price.Price;
import javax.inject.Named;


@Named
@AllArgsConstructor
public class TariffCoordinatesUseCase {
    private final CoordinatesPriceProvider coordinatesPriceProvider;

    public CoordinatesGroup checkCoordinates(CoordinatesGroup coordinatesGroup){
        var latMin = coordinatesPriceProvider.getLatitudeMin();
        var latMax = coordinatesPriceProvider.getLatitudeMax();
        var longMin = coordinatesPriceProvider.getLongitudeMin();
        var longMax = coordinatesPriceProvider.getLongitudeMax();

        var curDep = coordinatesGroup.departure();
        var curDes = coordinatesGroup.destination();

        if (curDep.latitude() < latMin || curDep.latitude() > latMax || curDep.longitude() < longMin || curDep.longitude() > longMax){
            throw new IllegalArgumentException("Значение depatrure заходят за границы конфигурационного файла");
        }
        if (curDes.latitude() < latMin || curDes.latitude() > latMax || curDes.longitude() < longMin || curDes.longitude() > longMax){
            throw new IllegalArgumentException("Значение destination заходят за границы конфигурационного файла");
        }
        return coordinatesGroup;
    }

    public Price calc(Price curPrice, CoordinatesGroup coordinatesGroup){
        return curPrice.calculateWithDistance(coordinatesGroup.calculateDistance());
    }

}
