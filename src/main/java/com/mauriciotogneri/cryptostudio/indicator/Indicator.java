package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.strategy.Strategy.StrategyType;
import com.mauriciotogneri.cryptostudio.type.Interval;

public abstract class Indicator
{
    public abstract void update(PriceData priceData);

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
                return new SMA(Interval.fromCode(input.interval), input.smaPeriod, input.sma1, input.sma2);

            case EMACROSS:
            case EMAGAIN:
            case EMASPREAD:
                return new EMA();

            default:
                throw new RuntimeException("Invalid strategy: " + type);
        }
    }
}