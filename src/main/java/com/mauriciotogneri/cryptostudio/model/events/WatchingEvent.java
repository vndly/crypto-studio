package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class WatchingEvent extends Event
{
    public final double price;

    public WatchingEvent(PriceData priceData)
    {
        super("watching", priceData.time());

        this.price = Decimal.round(priceData.price());
    }
}