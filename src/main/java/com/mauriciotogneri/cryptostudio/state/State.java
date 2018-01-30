package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public abstract class State
{
    public abstract State update(PriceData priceData);
}