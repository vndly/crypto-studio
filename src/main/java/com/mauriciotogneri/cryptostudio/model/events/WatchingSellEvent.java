package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class WatchingSellEvent extends Event
{
    public final double price;

    public WatchingSellEvent(PriceData priceData)
    {
        super("watching_sell", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
    }
}