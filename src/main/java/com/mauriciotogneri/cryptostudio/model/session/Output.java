package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;

import java.util.ArrayList;
import java.util.List;

public class Output
{
    private final Input input;
    private final List<Event> events;

    public Output(Input input)
    {
        this.input = input;
        this.events = new ArrayList<>();
    }

    public void event(Event event)
    {
        events.add(event);
    }
}