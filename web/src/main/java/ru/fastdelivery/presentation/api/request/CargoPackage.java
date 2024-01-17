package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.fastdelivery.domain.common.OuterDimensions.OuterDimensions;
import ru.fastdelivery.domain.common.length.Length;

import java.math.BigInteger;

public record CargoPackage(
        @Schema(description = "Вес упаковки, граммы", example = "5667.45")
        BigInteger weight,
        BigInteger length,
        BigInteger width,
        BigInteger height,
        OuterDimensions outerDimensions
) {
        public CargoPackage(BigInteger weight, BigInteger length,
                            BigInteger width,
                            BigInteger height, OuterDimensions outerDimensions) {
                this.weight = weight;
                this.length = length;
                this.width = width;
                this.height = height;
                this.outerDimensions = new OuterDimensions(new Length(length),new Length(width),new Length(height));
        }


}
