package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.strategy.Strategy.StrategyType;
import com.mauriciotogneri.cryptostudio.type.Interval;

public abstract class Indicator
{
    private boolean triggered = false;

    public abstract boolean isTriggered(PriceData priceData);

    public void update(PriceData priceData)
    {
        triggered = isTriggered(priceData);
    }

    public boolean isTriggered()
    {
        return triggered;
    }

    public static Indicator fromStrategy(StrategyType type, Input input)
    {
        switch (type)
        {
            case LOSS:
                return new Last24Hours(input.buyValue, Interval.fromCode(input.interval));

            case HIGHBB:
            case LOWBB:
                return new BB();

            case SMACROSS:
            case SMAGAIN:
            case SMASPREAD:
                return new SMA();

            case EMACROSS:
            case EMAGAIN:
            case EMASPREAD:
                return new EMA();

            default:
                throw new RuntimeException("Invalid strategy: " + type);
        }
    }
}