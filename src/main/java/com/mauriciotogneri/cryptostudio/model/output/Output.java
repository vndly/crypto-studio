package com.mauriciotogneri.cryptostudio.model.output;

import com.mauriciotogneri.cryptostudio.analyzer.Parameters;
import com.mauriciotogneri.cryptostudio.model.events.Event;

import java.util.ArrayList;
import java.util.List;

public class Output
{
    private final Parameters parameters;
    private final List<Event> events;

    public Output(Parameters parameters)
    {
        this.parameters = parameters;
        this.events = new ArrayList<>();
    }

    public void event(Event event)
    {
        events.add(event);
    }
}