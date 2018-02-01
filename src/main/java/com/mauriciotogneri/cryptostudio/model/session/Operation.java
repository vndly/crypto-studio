package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;

import java.util.ArrayList;
import java.util.List;

public class Operation
{
    private final List<Event> events;

    public Operation()
    {
        this.events = new ArrayList<>();
    }

    public boolean isEmpty()
    {
        return events.isEmpty();
    }

    public void event(Event event)
    {
        events.add(event);
    }
}