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
        int index = 0;
        int size = configuration.size();
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
                                                                for (Integer smaCrossCandles : configuration.smaCrossCandles)
                                                                {
                                                                    for (Integer emaPeriod : configuration.emaPeriod)
                                                                    {
                                                                        for (Integer ema1 : configuration.ema1)
                                                                        {
                                                                            for (Integer ema2 : configuration.ema2)
                                                                            {
                                                                                for (Integer emaCrossCandles : configuration.emaCrossCandles)
                                                                                {
                                                                                    for (Integer bbPeriod : configuration.bbPeriod)
                                                                                    {
                                                                                        for (Integer bbSma : configuration.bbSma)
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
                                                                                                                    sma2,
                                                                                                                    smaCrossCandles,
                                                                                                                    emaPeriod,
                                                                                                                    ema1,
                                                                                                                    ema2,
                                                                                                                    emaCrossCandles,
                                                                                                                    bbPeriod,
                                                                                                                    bbSma);

                                                                                            Output output = simulate(input, index++, size);
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
                                }
                            }
                        }
                    }
                }
            }
        }

        return outputs;
    }

    public Output simulate(Input input, int index, int size)
    {
        long startTime = System.currentTimeMillis();

        Output output = new Output(index + 1, input);
        Source source = input.source();
        Session session = new Session(input, output);
        List<PriceData> priceList = source.priceData(input);
        State state = new WatchingBuyState(session, new Operation(), priceList.get(0));

        double lastSma1 = 0;
        double lastSma2 = 0;
        double lastEma1 = 0;
        double lastEma2 = 0;

        for (PriceData priceData : priceList)
        {
            double sma1 = priceData.sma1();
            double sma2 = priceData.sma2();

            if ((sma1 != 0) && (sma1 != lastSma1))
            {
                lastSma1 = sma1;
                output.sma1(priceData.time(), sma1);
            }

            if ((sma2 != 0) && (sma2 != lastSma2))
            {
                lastSma2 = sma2;
                output.sma2(priceData.time(), sma2);
            }

            double ema1 = priceData.ema1();
            double ema2 = priceData.ema2();

            if ((ema1 != 0) && (ema1 != lastEma1))
            {
                lastEma1 = ema1;
                output.ema1(priceData.time(), ema1);
            }

            if ((ema2 != 0) && (ema2 != lastEma2))
            {
                lastEma2 = ema2;
                output.ema2(priceData.time(), ema2);
            }

            state = state.update(priceData);
        }

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Simulation time (%s/%s): %s ms", index + 1, size, endTime - startTime));

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