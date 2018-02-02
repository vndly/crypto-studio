package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;
import com.mauriciotogneri.cryptostudio.model.events.TradeBuyEvent;

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