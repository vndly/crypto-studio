package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

/**
 * Buy as soon as the pair drops the amount of percentages specified.
 * The percentage is the 24h change specified buy your exchange.
 * Possible buy_value example:
 * -7 → buy if the pair has fallen 7% or more in the last 24h
 * 7 → positive numbers are converted to negative
 */
public class LOSS extends Strategy
{
    private final double buyValue;

    public LOSS(double buyValue)
    {
        this.buyValue = buyValue;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        double last24H = priceData.last24H();

        if (last24H != 0)
        {
            double limit = Percentage.decreaseOf(buyValue, last24H);

            return (priceData.price() < limit);
        }
        else
        {
            return false;
        }
    }
}