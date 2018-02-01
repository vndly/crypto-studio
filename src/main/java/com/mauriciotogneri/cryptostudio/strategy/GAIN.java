package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

/**
 * Sell as soon as the current price reaches the profit specified.
 * <p>
 * Possible sell_value example:
 * sell_value = 2.5 → sell if profit is 2.5% or more.
 * -10 → sell if profit is -10% or more.
 */
public class GAIN extends Strategy
{
    private final double sellValue;
    private final double baseValue;

    public GAIN(double sellValue, double baseValue)
    {
        this.sellValue = sellValue;
        this.baseValue = baseValue;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        double limit = Percentage.increaseOf(sellValue, baseValue);

        return (priceData.price() > limit);
    }
}