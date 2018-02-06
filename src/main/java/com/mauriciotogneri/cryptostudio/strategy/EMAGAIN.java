package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Percentage;

/**
 * Buy as soon as the price goes a percentage below the specified EMA line.
 * ALL_buy_value is a percentage below or above the lowest of the 2 EMA lines.
 * Possible example:
 * -1 → buy if the current price is 1% below (or lower) the lowest EMA line
 * 1 → buy if the current price is 1% above (or higher) the lowest EMA line
 */
public class EMAGAIN extends Strategy
{
    private final double buyValue;

    public EMAGAIN(double buyValue)
    {
        this.buyValue = buyValue;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        double ema1 = priceData.ema1();
        double ema2 = priceData.ema2();

        if ((ema1 != 0) && (ema2 != 0))
        {
            double lowestEMA = Math.min(ema1, ema2);

            if (buyValue < 0)
            {
                return priceData.price() < Percentage.decreaseOf(buyValue, lowestEMA);
            }
            else
            {
                return priceData.price() > Percentage.increaseOf(buyValue, lowestEMA);
            }
        }
        else
        {
            return false;
        }
    }
}