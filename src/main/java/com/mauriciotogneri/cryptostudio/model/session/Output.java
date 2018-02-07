package com.mauriciotogneri.cryptostudio.model.session;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.type.Maximize;
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
            sumPercentage += current.percentageProfit();
        }

        totalProfit = Decimal.roundPrice(sumTotal);
        averagePercentageProfit = Decimal.roundPercentage(sumPercentage / operations.size());
    }

    private int profitableOperations()
    {
        int result = 0;

        for (Operation operation : operations)
        {
            if (operation.totalProfit() > 0)
            {
                result++;
            }
        }

        return result;
    }

    private int percentageProfitableOperations()
    {
        return (int) ((double) profitableOperations() / (double) operations.size());
    }

    private Summary summary()
    {
        return new Summary(input, totalProfit, averagePercentageProfit);
    }

    public int compareTo(Output output, Maximize maximize)
    {
        switch (maximize)
        {
            case OPERATIONS:
                return Double.compare(output.operations.size(), operations.size());

            case PROFITABLE_OPERATIONS:
                return Double.compare(output.profitableOperations(), profitableOperations());

            case PERCENTAGE_PROFITABLE_OPERATIONS:
                return Double.compare(output.percentageProfitableOperations(), percentageProfitableOperations());

            case TOTAL_PROFIT:
                return Double.compare(output.totalProfit, totalProfit);

            case AVERAGE_PERCENTAGE_PROFIT:
                return Double.compare(output.averagePercentageProfit, averagePercentageProfit);
        }

        throw new RuntimeException("Invalid maximize function");
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