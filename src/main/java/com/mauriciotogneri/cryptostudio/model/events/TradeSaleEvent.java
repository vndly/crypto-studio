package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class TradeSaleEvent extends Event
{
    public final double price;
    public final double amount;
    public final double total;
    public final double profit;

    public TradeSaleEvent(PriceData priceData, TradeBuyEvent tradeBuyEvent)
    {
        super("sale", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
        this.amount = tradeBuyEvent.amount;
        this.total = Decimal.roundPrice(price * amount);
        this.profit = Decimal.roundPercentage((total * 100 / tradeBuyEvent.total) - 100);
    }
}