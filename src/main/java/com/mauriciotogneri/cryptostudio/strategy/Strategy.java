package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.type.Interval;

public abstract class Strategy
{
    public enum StrategyType
    {
        GAIN,
        LOSS,
        HIGHBB,
        LOWBB,
        SMACROSS,
        SMAGAIN,
        SMASPREAD,
        EMACROSS,
        EMAGAIN,
        EMASPREAD
    }

    public abstract boolean update(PriceData priceData);

    public static Strategy fromString(StrategyType type, Input input, double value)
    {
        switch (type)
        {
            case GAIN:
                return new GAIN(input.sellValue, value);

            case LOSS:
                return new LOSS(input.buyValue, Interval.fromCode(input.interval));

            case HIGHBB:
                return new HIGHBB();

            case LOWBB:
                return new LOWBB();

            case SMACROSS:
                return new SMACROSS();

            case SMAGAIN:
                return new SMAGAIN();

            case SMASPREAD:
                return new SMASPREAD();

            case EMACROSS:
                return new EMACROSS();

            case EMAGAIN:
                return new EMAGAIN();

            case EMASPREAD:
                return new EMASPREAD();

            default:
                throw new RuntimeException("Invalid strategy: " + type);
        }
    }
}