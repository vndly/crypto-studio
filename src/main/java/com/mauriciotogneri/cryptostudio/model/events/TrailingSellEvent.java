package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class TrailingSellEvent extends Event
{
    public final double price;

    public TrailingSellEvent(PriceData priceData)
    {
        super("trailing_sell", priceData.time());

        this.price = Decimal.round(priceData.price());
    }
}