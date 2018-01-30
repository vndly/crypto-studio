package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.PriceData;

public class WatchingState extends State
{
    @Override
    public State update(PriceData priceData)
    {
        return this;
    }
}