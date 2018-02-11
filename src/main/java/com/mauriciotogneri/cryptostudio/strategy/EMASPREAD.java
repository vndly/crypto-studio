package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

/**
 * Buy as soon as EMA spread reaches your specified value.
 * ALL_buy_value is a percentage spread difference between EMA1 (slow) and EMA2. (fast)
 * Calculation: ((EMA2 / EMA1) - 1) * 100% → result of this calculation is the spread.
 * ALL_buy_value example:
 * -1 → buy if the current spread is -1% or smaller (EMA2 is lower than EMA1)
 * 1 → buy if the current spread is 1% or bigger (EMA2 is higher than EMA1)
 */
public class EMASPREAD extends MASPREAD
{
    public EMASPREAD(double buyValue)
    {
        super(buyValue);
    }

    @Override
    protected double spread(PriceData priceData)
    {
        double ema1 = priceData.ema1();
        double ema2 = priceData.ema2();

        if ((ema1 != 0) && (ema2 != 0))
        {
            return ((ema2 / ema1) - 1) * 100;
        }
        else
        {
            return 0;
        }
    }
}