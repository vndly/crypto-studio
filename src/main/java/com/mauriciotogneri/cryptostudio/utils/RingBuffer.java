package com.mauriciotogneri.cryptostudio.utils;

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
}