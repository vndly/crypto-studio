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

    private final double weightEma1;
    private final double weightEma2;
    private final GroupedRingBuffer ema1;
    private final GroupedRingBuffer ema2;

    public EMA(Interval interval, int smaPeriod, int ema1, int ema2)
    {
        int period = smaPeriod / interval.inSeconds();

        this.weightEma1 = 2d / (ema1 + 1d);
        this.weightEma2 = 2d / (ema2 + 1d);
        this.ema1 = new GroupedRingBuffer(period, ema1);
        this.ema2 = new GroupedRingBuffer(period, ema2);
    }

    @Override
    public void update(PriceHistory priceHistory)
    {
        if (ema1.isFull())
        {
            currentEma1 = ema(priceHistory.price(), currentEma1, weightEma1);
        }
        else
        {
            if (ema1.update(priceHistory))
            {
                currentEma1 = ema1.average();
            }
        }

        if (ema2.isFull())
        {
            currentEma2 = ema(priceHistory.price(), currentEma2, weightEma2);
        }
        else
        {
            if (ema2.update(priceHistory))
            {
                currentEma2 = ema2.average();
            }
        }
    }

    private double ema(double currentPrice, double previous, double weight)
    {
        return ((currentPrice - previous) * weight) + previous;
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