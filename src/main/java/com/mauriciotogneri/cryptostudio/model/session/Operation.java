package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;
import com.mauriciotogneri.cryptostudio.model.events.PurchaseEvent;

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

    public PurchaseEvent purchase()
    {
        for (Event event : events)
        {
            if (event instanceof PurchaseEvent)
            {
                return (PurchaseEvent) event;
            }
        }

        throw new RuntimeException("No purchase event found!");
    }
}