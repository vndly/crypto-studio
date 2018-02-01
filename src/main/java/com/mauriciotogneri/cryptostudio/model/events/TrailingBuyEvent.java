package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.util.Decimal;

public class TrailingBuyEvent extends Event
{
    public final double price;

    public TrailingBuyEvent(PriceData priceData)
    {
        super("trailing_buy", priceData.time());

        this.price = Decimal.roundPrice(priceData.price());
    }
}