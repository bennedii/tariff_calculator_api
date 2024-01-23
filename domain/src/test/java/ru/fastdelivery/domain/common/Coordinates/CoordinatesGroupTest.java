package ru.fastdelivery.domain.common.Coordinates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.coordinates.Coordinates;
import ru.fastdelivery.domain.common.coordinates.CoordinatesGroup;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesGroupTest {
    @Test
    @DisplayName("Проверка метода, который считает расстояние")
    void calculateDistanceTest(){
        CoordinatesGroup coordinatesGroup = new CoordinatesGroup(new Coordinates(77.1539,-139.398), new Coordinates(-77.1804,-139.55));
        double actual = coordinatesGroup.calculateDistance();
        double expected = 17161.19;
        assertThat(actual).isEqualTo(expected);
    }


}
