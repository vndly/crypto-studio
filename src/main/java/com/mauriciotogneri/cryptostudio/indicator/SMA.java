package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.GroupedRingBuffer;

public class SMA extends Indicator
{
    private final GroupedRingBuffer sma1;
    private final GroupedRingBuffer sma2;

    public SMA(Interval interval, int smaPeriod, int sma1, int sma2)
    {
        int period = smaPeriod / interval.inSeconds();

        this.sma1 = new GroupedRingBuffer(period, sma1);
        this.sma2 = new GroupedRingBuffer(period, sma2);
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        sma1.update(priceHistory);
        sma2.update(priceHistory);
    }

    public double sma1()
    {
        return sma1.isFull() ? sma1.average() : 0;
    }

    public double sma2()
    {
        return sma2.isFull() ? sma2.average() : 0;
    }
}