package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

/**
 * Buy as soon as SMA spread reaches your specified value.
 * ALL_buy_value is a percentage spread difference between SMA1 (slow) and SMA2 (fast)
 * Calculation: ((SMA2 / SMA1) - 1) * 100% → result of this calculation is the spread.
 * Possible ALL_buy_value example:
 * -1 → buy if the current spread is -1% or smaller (SMA2 is LOWER than SMA1)
 * 0.5 → buy if the current spread is 0.5% or bigger (SMA2 is HIGHER than SMA1)
 */
public class SMASPREAD extends MASPREAD
{
    public SMASPREAD(double buyValue)
    {
        super(buyValue);
    }

    @Override
    protected double spread(PriceData priceData)
    {
        double sma1 = priceData.sma1();
        double sma2 = priceData.sma2();

        if ((sma1 != 0) && (sma2 != 0))
        {
            return ((sma2 / sma1) - 1) * 100;
        }
        else
        {
            return 0;
        }
    }
}