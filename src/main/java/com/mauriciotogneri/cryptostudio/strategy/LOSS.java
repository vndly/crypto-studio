package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.indicator.Last24Hours;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;

/**
 * Buy as soon as the pair drops the amount of percentages specified.
 * The percentage is the 24h change specified buy your exchange.
 * Possible buy_value example:
 * -7 → buy if the pair has fallen 7% or more in the last 24h
 * 7 → positive numbers are converted to negative
 */
public class LOSS extends Strategy
{
    private final Last24Hours indicator;

    public LOSS(Last24Hours indicator)
    {
        this.indicator = indicator;
    }

    @Override
    public boolean update(PriceData priceData)
    {
        return indicator.isTriggered(priceData);
    }
}