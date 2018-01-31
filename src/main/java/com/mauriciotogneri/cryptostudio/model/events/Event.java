package com.mauriciotogneri.cryptostudio.model.events;

public abstract class Event
{
    protected final long time;

    protected Event(long time)
    {
        this.time = time;
    }
}