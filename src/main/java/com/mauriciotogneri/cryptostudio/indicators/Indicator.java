package com.mauriciotogneri.cryptostudio.indicators;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public abstract class Indicator
{
    public abstract void update(PriceData priceData);
}