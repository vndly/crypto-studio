package com.mauriciotogneri.cryptostudio.util;

public class RingBuffer
{
    private final double[] ring;
    private int position = 0;
    private boolean isFull = false;

    public RingBuffer(int size)
    {
        this.ring = new double[size];
    }

    public void add(double value)
    {
        ring[position] = value;
        position = (position + 1) % ring.length;
        isFull |= (position == 0);
    }

    public boolean isFull()
    {
        return isFull;
    }

    public double oldest()
    {
        return ring[position];
    }

    public double newest()
    {
        if (position > 0)
        {
            return ring[position - 1];
        }
        else
        {
            return ring[ring.length - 1];
        }
    }

    public double sum()
    {
        double sum = 0;

        for (double value : ring)
        {
            sum += value;
        }

        return sum;
    }

    public double average()
    {
        return sum() / ring.length;
    }

    public double[] values()
    {
        int index = 0;
        double[] values = new double[ring.length];

        for (int i = position; i < ring.length; i++)
        {
            values[index++] = ring[i];
        }

        for (int i = 0; i < position; i++)
        {
            values[index++] = ring[i];
        }

        return values;
    }
}