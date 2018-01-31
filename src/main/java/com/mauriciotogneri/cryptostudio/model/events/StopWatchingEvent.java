package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.util.Decimal;

public class StopWatchingEvent extends Event
{
    public final double price;

    public StopWatchingEvent(long time, double price)
    {
        super("stop_watching", time);

        this.price = Decimal.round(price);
    }
}