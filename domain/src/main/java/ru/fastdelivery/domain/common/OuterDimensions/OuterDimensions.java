package ru.fastdelivery.domain.common.OuterDimensions;

import ru.fastdelivery.domain.common.length.Length;

import java.math.BigInteger;

/**
 * Габариты
 *
 * @param length Длина
 * @param width Ширина
 * @param height Высота
 */
public record OuterDimensions(
        Length length,
        Length width,
        Length height
){
        public OuterDimensions(Length length, Length width, Length height) {
        var maxLength = BigInteger.valueOf(Length.getMaxDimensionsValueInMms());
        this.length = length;
        this.width = width;
        this.height = height;
        if (length.isGreaterThan(maxLength)){
            throw new IllegalArgumentException("Length cannot exceed the maximum value");
        }
        if (width.isGreaterThan(maxLength)){
            throw new IllegalArgumentException("Weight cannot exceed the maximum value");
        }
        if (height.isGreaterThan(maxLength)){
            throw new IllegalArgumentException("Height cannot exceed the maximum value");
        }
    }



}
