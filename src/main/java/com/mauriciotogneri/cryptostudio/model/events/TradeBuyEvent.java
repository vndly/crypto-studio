package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class TradeBuyEvent extends Event
{
    public final double price;
    public final double amount;
    public final double total;

    public TradeBuyEvent(PriceData priceData, double cost)
    {
        super("buy", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
        this.amount = Decimal.roundPrice(cost / price);
        this.total = Decimal.roundPrice(price * amount);
    }
}