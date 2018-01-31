package com.mauriciotogneri.cryptostudio.model.events;

import com.mauriciotogneri.cryptostudio.util.Timestamp;

public abstract class Event
{
    protected final String type;
    protected final long time;
    protected final String date;

    protected Event(String type, long time)
    {
        this.type = type;
        this.time = time;
        this.date = Timestamp.date(time);
    }
}