package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VolumeShipmentTest {

    @Test
    void testSumOfDoubleList(){
        List<Double> doubles = new ArrayList<>();
        doubles.add(0.1);
        doubles.add(0.3);
        doubles.add(0.2);
        double value = 0.6;
        var shipment = new VolumeShipment(doubles, new CurrencyFactory(code -> true).create("RUB"));
        assertThat(shipment.volumeAllPackages().doubleValue()).isEqualTo(value);
    }
}
