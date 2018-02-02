package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.util.Decimal;

import java.util.ArrayList;
import java.util.List;

public class Output
{
    private final Input input;
    private final List<Operation> operations;
    private double profitTotal = 0;
    private double profitPercentage = 0;

    public Output(Input input)
    {
        this.input = input;
        this.operations = new ArrayList<>();
    }

    public void operation(Operation operation)
    {
        operations.add(operation);

        double sumTotal = 0;
        double sumPercentage = 0;

        for (Operation current : operations)
        {
            sumTotal += current.profitTotal();
            sumPercentage += current.profitPercentage();
        }

        profitTotal = Decimal.roundPrice(sumTotal);
        profitPercentage = Decimal.roundPercentage(sumPercentage / operations.size());
    }
}