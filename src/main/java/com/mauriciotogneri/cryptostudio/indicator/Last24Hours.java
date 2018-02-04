package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.RingBuffer;

public class Last24Hours extends Indicator
{
    private final RingBuffer ring;

    public Last24Hours(Interval interval)
    {
        this.ring = new RingBuffer(interval.onDaySize());
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        ring.add(priceHistory.price());
    }

    public double oldest()
    {
        return ring.isFull() ? ring.oldest() : 0;
    }
}