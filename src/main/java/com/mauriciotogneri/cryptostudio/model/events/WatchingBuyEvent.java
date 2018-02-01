package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class WatchingBuyEvent extends Event
{
    public final double price;

    public WatchingBuyEvent(PriceData priceData)
    {
        super("watching_buy", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
    }
}