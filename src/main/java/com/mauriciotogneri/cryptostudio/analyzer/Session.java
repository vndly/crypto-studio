package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.output.Output;

public class Session
{
    public final Output output;
    public final Parameters parameters;

    public Session(Output output, Parameters parameters)
    {
        this.output = output;
        this.parameters = parameters;
    }
}