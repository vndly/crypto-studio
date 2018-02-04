package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.configuration.Configuration;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Output;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.state.State;
import com.mauriciotogneri.cryptostudio.state.WatchingBuyState;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Studio
{
    public List<Output> run(Configuration configuration)
    {
        List<Output> outputs = new ArrayList<>();

        for (String source : configuration.source)
        {
            for (String pair : configuration.pair)
            {
                for (String interval : configuration.interval)
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
                                                    for (Integer smaPeriod : configuration.smaPeriod)
                                                    {
                                                        for (Integer sma1 : configuration.sma1)
                                                        {
                                                            for (Integer sma2 : configuration.sma2)
                                                            {
                                                                Input input = new Input(source,
                                                                                        pair,
                                                                                        interval,
                                                                                        maxCost,
                                                                                        buyStrategy,
                                                                                        buyValue,
                                                                                        trailingBuy,
                                                                                        sellStrategy,
                                                                                        sellValue,
                                                                                        trailingProfit,
                                                                                        stopLossTrigger,
                                                                                        smaPeriod,
                                                                                        sma1,
                                                                                        sma2);

                                                                Output output = simulate(input);
                                                                outputs.add(output);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return outputs;
    }

    public Output simulate(Input input)
    {
        long startTime = System.currentTimeMillis();

        Output output = new Output(input);
        Source source = input.source();
        Session session = new Session(input, output);
        List<PriceData> priceList = source.priceData(input);
        State state = new WatchingBuyState(session, new Operation(), priceList.get(0));

        for (PriceData priceData : priceList)
        {
            double sma1 = priceData.sma1();
            double sma2 = priceData.sma2();

            if (sma1 != 0)
            {
                output.sma1(priceData.time(), sma1);
            }

            if (sma2 != 0)
            {
                output.sma2(priceData.time(), sma2);
            }

            state = state.update(priceData);
        }

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Simulation time: %s ms", endTime - startTime));

        return output;
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length > 1)
        {
            Studio studio = new Studio();
            List<Output> outputs = studio.run(new Configuration(args[0]));

            String json = new GsonBuilder().setPrettyPrinting().create().toJson(outputs);
            Resource.save(new File(args[1]), json);
        }
        else
        {
            System.err.println("Usage: java -jar studio.jar INPUT_FILE OUTPUT_FILE");
        }
    }
}