package com.mauriciotogneri.cryptostudio.model.result;

public abstract class Event
{
    protected final long time;

    protected Event(long time)
    {
        this.time = time;
    }
}