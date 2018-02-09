package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

/**
 * Buy as soon as the price goes a percentage below the specified EMA line.
 * ALL_buy_value is a percentage below or above the lowest of the 2 EMA lines.
 * Possible example:
 * -1 → buy if the current price is 1% below (or lower) the lowest EMA line
 * 1 → buy if the current price is 1% above (or higher) the lowest EMA line
 */
public class EMAGAIN extends MAGAIN
{
    public EMAGAIN(double buyValue)
    {
        super(buyValue);
    }

    @Override
    protected double lowest(PriceData priceData)
    {
        double ema1 = priceData.ema1();
        double ema2 = priceData.ema2();

        if ((ema1 != 0) && (ema2 != 0))
        {
            return Math.min(ema1, ema2);
        }
        else
        {
            return 0;
        }
    }
}