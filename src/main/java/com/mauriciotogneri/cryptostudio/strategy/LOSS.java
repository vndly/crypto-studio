package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.types.Interval;
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
    private CandleStick lastCandleStick;

    public LOSS(double buyValue, Interval interval)
    {
        this.buyValue = Math.abs(buyValue);
        this.ring = new RingBuffer(interval.onDaySize());
    }

    @Override
    public void update(CandleStick candleStick)
    {
        ring.add(candleStick.price());
        lastCandleStick = candleStick;
    }

    @Override
    public boolean isTriggered()
    {
        double limit = ring.oldest() - (ring.oldest() * buyValue / 100);

        return ring.isFull() && (lastCandleStick.price() < limit);
    }
}