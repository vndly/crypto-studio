package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.Percentage;
import com.mauriciotogneri.cryptostudio.util.RingBuffer;

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
    private final RingBuffer ring;

    public LOSS(double buyValue, Interval interval)
    {
        this.buyValue = buyValue;
        this.ring = new RingBuffer(interval.onDaySize());
    }

    @Override
    public boolean update(PriceData priceData)
    {
        ring.add(priceData.price());

        double limit = Percentage.decreaseOf(buyValue, ring.oldest());

        return ring.isFull() && (priceData.price() < limit);
    }
}