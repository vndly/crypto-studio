package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.Gson;
import com.mauriciotogneri.cryptostudio.analyzer.Analyzer;
import com.mauriciotogneri.cryptostudio.analyzer.Parameters;
import com.mauriciotogneri.cryptostudio.configuration.Configuration;
import com.mauriciotogneri.cryptostudio.result.Result;
import com.mauriciotogneri.cryptostudio.strategies.Strategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Studio
{
    public List<Result> run(Configuration configuration)
    {
        List<Result> results = new ArrayList<>();
        Analyzer analyzer = new Analyzer();

        for (String pair : configuration.pair)
        {
            for (Double maxCost : configuration.maxCost)
            {
                for (String buyStrategy : configuration.buyStrategy)
                {
                    for (Double buyValue : configuration.buyValue)
                    {
                        for (Double trailingBuy : configuration.trailingBuy)
                        {
                            for (String sellStrategy : configuration.sellStrategy)
                            {
                                for (Double sellValue : configuration.sellValue)
                                {
                                    for (Double trailingProfit : configuration.trailingProfit)
                                    {
                                        for (Double stopLossTrigger : configuration.stopLossTrigger)
                                        {
                                            Parameters parameters = new Parameters(pair,
                                                                                   maxCost,
                                                                                   Strategy.fromString(buyStrategy),
                                                                                   buyValue,
                                                                                   trailingBuy,
                                                                                   Strategy.fromString(sellStrategy),
                                                                                   sellValue,
                                                                                   trailingProfit,
                                                                                   stopLossTrigger);

                                            Result result = analyzer.run(parameters);
                                            results.add(result);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return results;
    }

    public static void output(List<Result> results, String filePath) throws Exception
    {
        String json = new Gson().toJson(results);

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(json);
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length > 1)
        {
            Studio studio = new Studio();
            List<Result> results = studio.run(new Configuration(args[0]));
            output(results, args[1]);
        }
        else
        {
            System.err.println("Usage: java -jar studio.jar INPUT_FILE OUTPUT_FILE");
        }
    }
}