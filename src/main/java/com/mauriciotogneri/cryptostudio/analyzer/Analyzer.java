package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.result.Result;

public class Analyzer
{
    public Result run(Parameters parameters)
    {
        Result result = new Result(parameters);

        // TODO: do magic stuff
        result.event();
        result.event();
        result.event();

        return result;
    }
}