package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.RingBuffer;

public class SMA extends Indicator
{
    private int periodIndex;
    private double periodSum;

    private final int period;
    private final RingBuffer sma1;
    private final RingBuffer sma2;

    public SMA(Interval interval, int period, int sma1, int sma2)
    {
        this.periodIndex = 0;
        this.periodSum = 0;

        this.period = period / interval.inSeconds();
        this.sma1 = new RingBuffer(sma1);
        this.sma2 = new RingBuffer(sma2);
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        periodSum += priceHistory.price();
        periodIndex++;

        if (periodIndex == period)
        {
            double average = periodSum / period;

            sma1.add(average);
            sma2.add(average);

            periodSum = 0;
            periodIndex = 0;
        }
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