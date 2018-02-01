package com.mauriciotogneri.cryptostudio.model.session;

import java.util.ArrayList;
import java.util.List;

public class Output
{
    private final Input input;
    private final List<Operation> operations;

    public Output(Input input)
    {
        this.input = input;
        this.operations = new ArrayList<>();
    }

    public void operation(Operation operation)
    {
        operations.add(operation);
    }
}