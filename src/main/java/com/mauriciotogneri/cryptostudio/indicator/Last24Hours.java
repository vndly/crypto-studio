package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.Percentage;
import com.mauriciotogneri.cryptostudio.util.RingBuffer;

public class Last24Hours extends Indicator
{
    private final double buyValue;
    private final RingBuffer ring;

    public Last24Hours(double buyValue, Interval interval)
    {
        this.buyValue = buyValue;
        this.ring = new RingBuffer(interval.onDaySize());
    }

    @Override
    public void update(PriceData priceData)
    {
        ring.add(priceData.price());
    }

    public boolean isTriggered(PriceData priceData)
    {
        double limit = Percentage.decreaseOf(buyValue, ring.oldest());

        return ring.isFull() && (priceData.price() < limit);
    }
}