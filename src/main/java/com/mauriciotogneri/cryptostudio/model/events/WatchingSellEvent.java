package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class WatchingSellEvent extends Event
{
    public final double price;
    public final double amount;

    public WatchingSellEvent(PriceData priceData, double maxCost)
    {
        super("purchase", priceData.time());

        this.price = Decimal.round(priceData.price());
        this.amount = Decimal.round(maxCost / price);
    }
}