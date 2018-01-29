package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.CandleStick;

/**
 * Sell as soon as the current price reaches the profit specified.
 *
 * Possible sell_value example:
 * sell_value = 2.5 → sell if profit is 2.5% or more.
 * -10 → sell if profit is -10% or more.
 */
public class GAIN extends Strategy
{
    private final double sellValue;

    public GAIN(double sellValue)
    {
        this.sellValue = sellValue;
    }

    @Override
    public void update(CandleStick candleStick)
    {
        // TODO
    }

    @Override
    public boolean isTriggered()
    {
        return false;
    }
}