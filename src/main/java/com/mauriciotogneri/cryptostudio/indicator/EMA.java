package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.util.GroupedRingBuffer;

/**
 * EMA = ((Current_Price - Previous_EMA) * Weight) + Previous_EMA
 */
public class EMA extends Indicator
{
    private double currentEma1;
    private double currentEma2;
    private double previousEma1;
    private double previousEma2;

    private final double weight;
    private final GroupedRingBuffer ema1;
    private final GroupedRingBuffer ema2;

    public EMA(Interval interval, int smaPeriod, int ema1, int ema2)
    {
        int period = smaPeriod / interval.inSeconds();

        this.weight = 2d / (period + 1d);
        this.ema1 = new GroupedRingBuffer(period, ema1);
        this.ema2 = new GroupedRingBuffer(period, ema2);
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        if (ema1.update(priceHistory))
        {
            updateEma1();
        }

        if (ema2.update(priceHistory))
        {
            updateEma2();
        }
    }

    private void updateEma1()
    {
        if (ema1.isFull())
        {
            if (previousEma1 == 0)
            {
                previousEma1 = ema1.average();
            }

            currentEma1 = ema(ema1.values(), previousEma1, weight);
            previousEma1 = currentEma1;
        }
    }

    private void updateEma2()
    {
        if (ema2.isFull())
        {
            if (previousEma2 == 0)
            {
                previousEma2 = ema2.average();
            }

            currentEma2 = ema(ema2.values(), previousEma2, weight);
            previousEma2 = currentEma2;
        }
    }

    private double ema(double[] values, double initialPrevious, double weight)
    {
        double previous = initialPrevious;
        double ema = 0;

        for (double value : values)
        {
            ema = ((value - previous) * weight) + previous;
            previous = ema;
        }

        return ema;
    }

    public double ema1()
    {
        return ema1.isFull() ? currentEma1 : 0;
    }

    public double ema2()
    {
        return ema2.isFull() ? currentEma2 : 0;
    }
}