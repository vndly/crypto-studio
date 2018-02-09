package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

/**
 * Buy as soon as the price goes a percentage below the specified SMA line.
 * ALL_buy_value is a percentage below or above the lowest of the 2 SMA lines.
 * Possible example:
 * -3 → buy if the current price is 3% below (or lower) the lowest SMA line
 * 3 → buy if the current price is 3% above (or higher) the lowest SMA line
 */
public class SMAGAIN extends MAGAIN
{
    public SMAGAIN(double buyValue)
    {
        super(buyValue);
    }

    @Override
    protected double lowest(PriceData priceData)
    {
        double sma1 = priceData.sma1();
        double sma2 = priceData.sma2();

        if ((sma1 != 0) && (sma2 != 0))
        {
            return Math.min(sma1, sma2);
        }
        else
        {
            return 0;
        }
    }
}