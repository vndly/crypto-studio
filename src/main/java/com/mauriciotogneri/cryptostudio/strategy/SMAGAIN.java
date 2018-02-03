package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.indicator.SMA;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

/**
 * Buy as soon as the price goes below the specified SMA line.
 * ALL_buy_value is a percentage below or above the lowest of the 2 SMA lines.
 * Possible example:
 * -3 → buy if the current price is 3% below (or lower) the lowest SMA line
 * 3 → buy if the current price is 3% above (or lower) the lowest SMA line
 */
public class SMAGAIN extends Strategy
{
    private final double buyValue;
    private final SMA indicator;

    public SMAGAIN(double buyValue, SMA indicator)
    {
        this.buyValue = buyValue;
        this.indicator = indicator;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        if (indicator.isFull())
        {
            double lowestSMA = Math.min(indicator.sma1(), indicator.sma2());

            if (buyValue < 0)
            {
                return priceData.price() < Percentage.decreaseOf(buyValue, lowestSMA);
            }
            else
            {
                return priceData.price() < Percentage.increaseOf(buyValue, lowestSMA);
            }
        }
        else
        {
            return false;
        }
    }
}