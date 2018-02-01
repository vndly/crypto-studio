package com.mauriciotogneri.cryptostudio.trailing;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public abstract class Trailing
{
    public enum TrailingState
    {
        CONTINUE,
        ROLLBACK,
        BUY,
        SELL
    }

    public abstract TrailingState update(PriceData priceData);
}