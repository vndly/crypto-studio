package com.mauriciotogneri.cryptostudio.app;

import com.mauriciotogneri.cryptostudio.analyzer.Analyzer;
import com.mauriciotogneri.cryptostudio.parameters.Parameters;
import com.mauriciotogneri.cryptostudio.strategies.Strategy;

public class Studio
{
    public void run(Parameters parameters)
    {
        Analyzer analyzer = new Analyzer();

        for (Double maxCost : parameters.maxCost)
        {
            for (String buyStrategy : parameters.buyStrategy)
            {
                for (Double buyValue : parameters.buyValue)
                {
                    for (Double trailingBuy : parameters.trailingBuy)
                    {
                        for (String sellStrategy : parameters.sellStrategy)
                        {
                            for (Double sellValue : parameters.sellValue)
                            {
                                for (Double trailingProfit : parameters.trailingProfit)
                                {
                                    for (Double stopLossTrigger : parameters.stopLossTrigger)
                                    {
                                        analyzer.run(maxCost,
                                                     Strategy.fromString(buyStrategy),
                                                     buyValue,
                                                     trailingBuy,
                                                     Strategy.fromString(sellStrategy),
                                                     sellValue,
                                                     trailingProfit,
                                                     stopLossTrigger);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length > 0)
        {
            Studio studio = new Studio();
            studio.run(new Parameters(args[0]));
        }
        else
        {
            System.err.println("Usage: java -jar studio.jar PROPERTIES_PATH");
        }
    }
}