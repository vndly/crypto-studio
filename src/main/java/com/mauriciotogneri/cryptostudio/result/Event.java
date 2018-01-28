package com.mauriciotogneri.cryptostudio.result;

public abstract class Event
{
    protected final long time;

    protected Event(long time)
    {
        this.time = time;
    }
}