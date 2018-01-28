package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.analyzer.Analyzer;
import com.mauriciotogneri.cryptostudio.analyzer.Parameters;
import com.mauriciotogneri.cryptostudio.configuration.Configuration;
import com.mauriciotogneri.cryptostudio.result.Result;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
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
                                                                                   buyStrategy,
                                                                                   buyValue,
                                                                                   trailingBuy,
                                                                                   sellStrategy,
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

    public static void main(String[] args) throws Exception
    {
        if (args.length > 1)
        {
            Studio studio = new Studio();
            List<Result> results = studio.run(new Configuration(args[0]));

            String json = new GsonBuilder().setPrettyPrinting().create().toJson(results);
            Resource.save(new File(args[1]), json);
        }
        else
        {
            System.err.println("Usage: java -jar studio.jar INPUT_FILE OUTPUT_FILE");
        }
    }
}