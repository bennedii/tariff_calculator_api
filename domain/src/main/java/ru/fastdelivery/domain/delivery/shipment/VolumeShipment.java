package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.currency.Currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @param volumes объемы грузов
 * @param currency валюта объявленная для груза
 */
public record VolumeShipment(
        List<Double> volumes,
        Currency currency
) {

    public BigDecimal volumeAllPackages(){
        return BigDecimal.valueOf(((double) Math.round(volumes.stream().reduce(0.0, Double::sum) * 1000)) / 1000);
    }
}
