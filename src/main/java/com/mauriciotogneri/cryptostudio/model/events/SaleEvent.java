package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class SaleEvent extends Event
{
    public final double price;
    public final double amount;
    public final double total;
    public final double profit;

    public SaleEvent(PriceData priceData, PurchaseEvent purchaseEvent)
    {
        super("sale", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
        this.amount = purchaseEvent.amount;
        this.total = Decimal.roundPrice(price * amount);
        this.profit = Decimal.roundPercentage((total * 100 / purchaseEvent.total) - 100);
    }
}