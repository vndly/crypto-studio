package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.util.Decimal;

import java.util.ArrayList;
import java.util.List;

public class Output
{
    private final Input input;
    private final List<Operation> operations;
    private final List<double[]> sma1;
    private final List<double[]> sma2;
    private final List<double[]> ema1;
    private final List<double[]> ema2;
    private double profitTotal = 0;
    private double profitPercentage = 0;

    public Output(Input input)
    {
        this.input = input;
        this.operations = new ArrayList<>();
        this.sma1 = new ArrayList<>();
        this.sma2 = new ArrayList<>();
        this.ema1 = new ArrayList<>();
        this.ema2 = new ArrayList<>();
    }

    public void sma1(long time, double value)
    {
        sma1.add(new double[] {time, value});
    }

    public void sma2(long time, double value)
    {
        sma2.add(new double[] {time, value});
    }

    public void ema1(long time, double value)
    {
        ema1.add(new double[] {time, value});
    }

    public void ema2(long time, double value)
    {
        ema2.add(new double[] {time, value});
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