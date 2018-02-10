package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.type.Maximize;

import java.util.ArrayList;

public class Outputs extends ArrayList<Output>
{
    private final Integer maxResults;
    private final Maximize maximize;

    public Outputs(Integer maxResults, Maximize maximize)
    {
        super(maxResults);

        this.maxResults = maxResults;
        this.maximize = maximize;
    }

    public void addOutput(Output output)
    {
        int size = size();

        if (size < maxResults)
        {
            add(output);
            sort();
        }
        else
        {
            int lastPosition = size - 1;
            Output last = get(lastPosition);

            int comparison = last.compareTo(output, maximize);

            if (comparison == 1)
            {
                set(lastPosition, output);
                sort();
            }
        }
    }

    private void sort()
    {
        sort((o1, o2) -> o1.compareTo(o2, maximize));
    }
}