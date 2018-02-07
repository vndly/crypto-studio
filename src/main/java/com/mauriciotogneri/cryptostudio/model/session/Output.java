package com.mauriciotogneri.cryptostudio.model.session;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.util.Decimal;

import java.util.ArrayList;
import java.util.List;

public class Output implements Comparable<Output>
{
    private final Input input;
    private final List<Operation> operations;
    private final List<double[]> sma1;
    private final List<double[]> sma2;
    private final List<double[]> ema1;
    private final List<double[]> ema2;
    private double totalProfit = 0;
    private double averagePercentageProfit = 0;

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
            sumTotal += current.totalProfit();
            sumPercentage += current.averagePercentageProfit();
        }

        totalProfit = Decimal.roundPrice(sumTotal);
        averagePercentageProfit = Decimal.roundPercentage(sumPercentage / operations.size());
    }

    private Summary summary()
    {
        return new Summary(input, totalProfit, averagePercentageProfit);
    }

    @Override
    public int compareTo(Output output)
    {
        return Double.compare(output.totalProfit, totalProfit);
    }

    private static class Summary
    {
        private final Input input;
        private final double totalProfit;
        private final double averagePercentageProfit;

        private Summary(Input input, double totalProfit, double averagePercentageProfit)
        {
            this.input = input;
            this.totalProfit = totalProfit;
            this.averagePercentageProfit = averagePercentageProfit;
        }
    }

    @Override
    public String toString()
    {
        return new GsonBuilder().setPrettyPrinting().create().toJson(summary());
    }
}