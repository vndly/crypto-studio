package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.types.Interval;
import com.mauriciotogneri.cryptostudio.utils.Percentage;
import com.mauriciotogneri.cryptostudio.utils.RingBuffer;

/**
 * Buy as soon as the pair drops the amount of percentages specified.
 * The percentage is the 24h change specified buy your exchange.
 * <p>
 * Possible buy_value example:
 * buy_value = -7 → buy if the pair has fallen 7% or more in the last 24h
 * 7 → positive numbers are converted to negative. So result is the same as above.
 */
public class LOSS extends Strategy
{
    private final double buyValue;
    private final RingBuffer ring;
    private PriceData priceData;

    public LOSS(double buyValue, Interval interval)
    {
        this.buyValue = buyValue;
        this.ring = new RingBuffer(interval.onDaySize());
    }

    @Override
    public void update(PriceData newPriceData)
    {
        ring.add(newPriceData.price());
        priceData = newPriceData;
    }

    @Override
    public boolean isTriggered()
    {
        double limit = Percentage.decreaseOf(buyValue, ring.oldest());

        return ring.isFull() && (priceData.price() < limit);
    }
}