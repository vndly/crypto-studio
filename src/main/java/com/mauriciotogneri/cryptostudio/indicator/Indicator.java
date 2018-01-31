package com.mauriciotogneri.cryptostudio.indicator;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public abstract class Indicator
{
    public abstract void update(PriceData priceData);
}