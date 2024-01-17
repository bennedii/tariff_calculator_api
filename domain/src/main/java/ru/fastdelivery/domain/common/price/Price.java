package ru.fastdelivery.domain.common.price;

import ru.fastdelivery.domain.common.currency.Currency;

import java.math.BigDecimal;

/**
 * @param amount   значение цены
 * @param currency валюта цены
 */
public record Price(
        BigDecimal amount,
        Currency currency) {
    public Price {
        if (isLessThanZero(amount)) {
            throw new IllegalArgumentException("Price Amount cannot be below Zero!");
        }
    }

    private static boolean isLessThanZero(BigDecimal price) {
        return BigDecimal.ZERO.compareTo(price) > 0;
    }

    public Price multiply(BigDecimal amount) {
        return new Price(this.amount.multiply(amount), this.currency);
    }
    public Price getMostBiggerPrice(Price price){
        if(this.amount.doubleValue() > price.amount.doubleValue()){
            return this;
        }
        return price;
    }

    public Price max(Price price) {
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Cannot compare Prices in difference Currency!");
        }
        return new Price(this.amount.max(price.amount), this.currency);
    }

    public Price calculateWithDistance(double distance){

        if (distance > 450){
            double finalValue = this.amount.doubleValue() * (distance / 450);
            finalValue = (double) (Math.ceil(finalValue * 100) / 100);
            return new Price(BigDecimal.valueOf(finalValue), this.currency);
        }

        return this;
    }
}
