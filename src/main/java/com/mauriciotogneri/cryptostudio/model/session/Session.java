package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.model.events.Event;

public class Session
{
    public final Input input;
    private final Output output;

    public Session(Input input, Output output)
    {
        this.input = input;
        this.output = output;
    }

    public void event(Event event)
    {
        output.event(event);
    }
}