package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;

public abstract class Indicator
{
    public abstract void update(PriceHistory priceHistory);
}