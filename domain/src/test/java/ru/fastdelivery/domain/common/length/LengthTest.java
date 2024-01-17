package ru.fastdelivery.domain.common.length;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LengthTest {

    @Test
    @DisplayName("Попытка создать отрицательную длину -> исключение ")
    void whenGramsBelowZero_thenException() {
        var lengthMms = new BigInteger("-1");
        assertThatThrownBy(() -> new Length(lengthMms))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Проверка нормализации длины")
    void normalizeTest(){
        var normalValue = BigDecimal.valueOf(400);
        Length length = new Length(BigInteger.valueOf(389));
        assertThat(length.length().intValue()).isEqualTo(normalValue.intValue());
    }
    @Test
    @DisplayName("Проверка нормализации длины (вторая)")
    void normalizeTest2(){
        var normalValue = BigDecimal.valueOf(400);
        Length length = new Length(BigInteger.valueOf(400));
        assertThat(length.length().intValue()).isEqualTo(normalValue.intValue());
    }

}
