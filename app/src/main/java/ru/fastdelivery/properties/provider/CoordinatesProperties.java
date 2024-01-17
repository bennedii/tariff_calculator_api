package ru.fastdelivery.properties.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.fastdelivery.domain.common.coordinates.Coordinates;
import ru.fastdelivery.domain.common.coordinates.CoordinatesGroup;
import ru.fastdelivery.usecase.CoordinatesPriceProvider;


@ConfigurationProperties("coordinates")
@Getter
@Setter
public class CoordinatesProperties implements CoordinatesPriceProvider {

    public int latitudeMin;
    public int latitudeMax;

    public int longitudeMin;
    public int longitudeMax;


}
