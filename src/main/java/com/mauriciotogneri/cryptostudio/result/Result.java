package com.mauriciotogneri.cryptostudio.result;

import com.mauriciotogneri.cryptostudio.analyzer.Parameters;

import java.util.ArrayList;
import java.util.List;

public class Result
{
    private final Parameters parameters;
    private final List<Event> events;

    public Result(Parameters parameters)
    {
        this.parameters = parameters;
        this.events = new ArrayList<>();
    }

    public void event()
    {
        // TODO
    }
}