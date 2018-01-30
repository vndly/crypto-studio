package com.mauriciotogneri.cryptostudio.indicators;

import com.mauriciotogneri.cryptostudio.model.PriceData;

public abstract class Indicator
{
    public abstract void update(PriceData priceData);
}