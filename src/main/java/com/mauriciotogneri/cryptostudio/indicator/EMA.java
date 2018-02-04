package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.RingBuffer;

public class EMA extends Indicator
{
    private int periodIndex;
    private double periodSum;

    private final int period;
    private final RingBuffer ema1;
    private final RingBuffer ema2;

    public EMA(Interval interval, int period, int ema1, int ema2)
    {
        this.periodIndex = 0;
        this.periodSum = 0;

        this.period = period / interval.inSeconds();
        this.ema1 = new RingBuffer(ema1);
        this.ema2 = new RingBuffer(ema2);
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        periodSum += priceHistory.price();
        periodIndex++;

        if (periodIndex == period)
        {
            double average = periodSum / period;

            ema1.add(average);
            ema2.add(average);

            periodSum = 0;
            periodIndex = 0;
        }
    }

    public double ema1()
    {
        return ema1.isFull() ? ema1.average() * 2 : 0; // TODO: this is a test
    }

    public double ema2()
    {
        return ema2.isFull() ? ema2.average() * 2 : 0; // TODO: this is a test
    }
}