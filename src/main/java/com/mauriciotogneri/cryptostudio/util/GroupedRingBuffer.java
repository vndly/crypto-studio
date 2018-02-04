package com.mauriciotogneri.cryptostudio.util;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;

public class GroupedRingBuffer extends RingBuffer
{
    private int periodIndex;
    private final int period;

    public GroupedRingBuffer(int period, int size)
    {
        super(size);

        this.periodIndex = 0;
        this.period = period;
    }

    public boolean update(PriceHistory priceHistory)
    {
        periodIndex++;

        if (periodIndex == period)
        {
            add(priceHistory.price());

            periodIndex = 0;

            return true;
        }
        else
        {
            return false;
        }
    }
}