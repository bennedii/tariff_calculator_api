package ru.fastdelivery.domain.common.length;

import java.math.BigInteger;


/**
 * Общий класс длины
 *
 * @param length длина в милиметрах
 */
public record Length(BigInteger length) {
    private static final int MAX_LENGTH_DIMENSION = 1500;
    public Length(BigInteger length) {
        this.length = normalize(length);

        if (isLessThanZero(length)) {
            throw new IllegalArgumentException("Length cannot be below Zero!");
        }
    }


    private boolean isLessThanZero(BigInteger length) {
        return BigInteger.ZERO.compareTo(length) > 0;
    }


    private BigInteger normalize(BigInteger value){
        var c = value.intValue() % 50 > 0 ? value.intValue() / 50 * 50 + 50 : value.intValue();
        return BigInteger.valueOf(c);
    }

    public static int getMaxDimensionsValueInMms(){
        return MAX_LENGTH_DIMENSION;
    }

    public boolean isGreaterThan(BigInteger value){
        return length.compareTo(value) > 0;
    }

}
