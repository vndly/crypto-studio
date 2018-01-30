package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.result.Result;

public class Session
{
    public final Result result;
    public final Parameters parameters;

    public Session(Result result, Parameters parameters)
    {
        this.result = result;
        this.parameters = parameters;
    }
}