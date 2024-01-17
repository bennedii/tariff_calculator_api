package ru.fastdelivery.domain.common.Volume;

import ru.fastdelivery.domain.common.OuterDimensions.OuterDimensions;


public class Volume {
    public static double getVolume(OuterDimensions o){
         var val = o.height().length().doubleValue() * o.length().length().doubleValue() * o.width().length().doubleValue() / 1_000_000_000;
         val = (double) Math.round(val * 10000) / 10000;
         return val;
    }
}
