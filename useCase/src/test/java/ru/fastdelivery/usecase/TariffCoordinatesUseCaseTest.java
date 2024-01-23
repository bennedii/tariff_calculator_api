package ru.fastdelivery.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.coordinates.Coordinates;
import ru.fastdelivery.domain.common.coordinates.CoordinatesGroup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class TariffCoordinatesUseCaseTest {
    final CoordinatesPriceProvider coordinatesPriceProvider = mock(CoordinatesPriceProvider.class);
    final TariffCoordinatesUseCase tariffCoordinatesUseCase = new TariffCoordinatesUseCase(coordinatesPriceProvider);
    @Test
    @DisplayName("Проверка метода 'checkCoordinates' с координатами за пределами конфигурационного файла")
    void checkCoordinatesMethodTest(){
        var lamin = coordinatesPriceProvider.getLatitudeMin();
        var lamax = coordinatesPriceProvider.getLatitudeMax();
        var lomin = coordinatesPriceProvider.getLongitudeMin();
        var lomax = coordinatesPriceProvider.getLongitudeMax();
        CoordinatesGroup coordinatesGroup = new CoordinatesGroup(new Coordinates(lamin - 1, lomax + 1),new Coordinates(lamax + 1,lomin - 1));

        assertThatThrownBy(() -> tariffCoordinatesUseCase.checkCoordinates(coordinatesGroup))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    @DisplayName("Проверка метода 'checkCoordinates' с координатами в пределе конфигурационного файла")
    void checkCoordinatesMethodTest2(){
        var lamin = coordinatesPriceProvider.getLatitudeMin();
        var lamax = coordinatesPriceProvider.getLatitudeMax();
        var lomin = coordinatesPriceProvider.getLongitudeMin();
        var lomax = coordinatesPriceProvider.getLongitudeMax();
        CoordinatesGroup coordinatesGroup = new CoordinatesGroup(new Coordinates(lamin, lomax),new Coordinates(lamax,lomin));

        assertThat(coordinatesGroup).isEqualTo(tariffCoordinatesUseCase.checkCoordinates(coordinatesGroup));
    }



}
