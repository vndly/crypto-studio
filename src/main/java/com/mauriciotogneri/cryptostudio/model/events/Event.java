package com.mauriciotogneri.cryptostudio.model.events;

public abstract class Event
{
    protected final String type;
    protected final long time;

    protected Event(String type, long time)
    {
        this.type = type;
        this.time = time;
    }
}