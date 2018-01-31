package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.util.Decimal;

public class StartWatchingEvent extends Event
{
    public final double price;

    public StartWatchingEvent(long time, double price)
    {
        super("start_watching", time);

        this.price = Decimal.round(price);
    }
}