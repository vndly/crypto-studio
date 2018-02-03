package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;
import com.mauriciotogneri.cryptostudio.model.events.TradeBuyEvent;
import com.mauriciotogneri.cryptostudio.model.events.TradeSaleEvent;

import java.util.ArrayList;
import java.util.List;

public class Operation
{
    private final List<Event> events;

    public Operation()
    {
        this.events = new ArrayList<>();
    }

    public void event(Event event)
    {
        events.add(event);
    }

    public double buyPrice()
    {
        return purchase().price;
    }

    public double profitTotal()
    {
        return tradeSaleEvent().profitTotal;
    }

    public double profitPercentage()
    {
        return tradeSaleEvent().profitPercentage;
    }

    private TradeSaleEvent tradeSaleEvent()
    {
        return (TradeSaleEvent) events.get(events.size() - 1);
    }

    public TradeBuyEvent purchase()
    {
        for (Event event : events)
        {
            if (event instanceof TradeBuyEvent)
            {
                return (TradeBuyEvent) event;
            }
        }

        throw new RuntimeException("No purchase event found!");
    }
}