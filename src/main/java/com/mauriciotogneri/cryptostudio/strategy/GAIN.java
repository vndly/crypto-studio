package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

/**
 * Sell as soon as the current price reaches the profit specified.
 * Possible sell_value example:
 * 2.5 → sell if profit is 2.5% or more
 * -10 → sell if profit is -10% or more
 */
public class GAIN extends Strategy
{
    private final double limit;

    public GAIN(double sellValue, double baseValue)
    {
        this.limit = Percentage.increaseOf(sellValue, baseValue);
    }

    @Override
    public boolean update(PriceData priceData)
    {
        return (priceData.price() > limit);
    }
}