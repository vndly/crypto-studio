package com.mauriciotogneri.cryptostudio.strategy;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;

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

    public static Strategy buyStrategy(StrategyType type, Input input)
    {
        switch (type)
        {
            case LOSS:
                return new LOSS(input.buyValue);

            case HIGHBB:
                return new HIGHBB();

            case LOWBB:
                return new LOWBB();

            case SMACROSS:
                return new SMACROSS();

            case SMAGAIN:
                return new SMAGAIN(input.buyValue);

            case SMASPREAD:
                return new SMASPREAD(buyValue);

            case EMACROSS:
                return new EMACROSS();

            case EMAGAIN:
                return new EMAGAIN(input.buyValue);

            case EMASPREAD:
                return new EMASPREAD();

            default:
                throw new RuntimeException("Invalid strategy: " + type);
        }
    }

    public static Strategy sellStrategy(StrategyType type, Input input, double value)
    {
        switch (type)
        {
            case GAIN:
                return new GAIN(input.sellValue, value);

            default:
                throw new RuntimeException("Invalid strategy: " + type);
        }
    }
}