package com.mauriciotogneri.cryptostudio.model.session;

public class Session
{
    public final Input input;
    private final Output output;

    public Session(Input input, Output output)
    {
        this.input = input;
        this.output = output;
    }

    public void operation(Operation operation)
    {
        output.operation(operation);
    }
}