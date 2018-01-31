package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;

public class PurchaseEvent extends Event
{
    public final double price;
    public final double amount;

    public PurchaseEvent(PriceData priceData, double price, double amount)
    {
        super("purchase", priceData.time());

        this.price = price;
        this.amount = amount;
    }
}