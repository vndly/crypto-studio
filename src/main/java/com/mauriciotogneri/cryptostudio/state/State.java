package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.PriceData;

public abstract class State
{
    public abstract State update(PriceData priceData);
}