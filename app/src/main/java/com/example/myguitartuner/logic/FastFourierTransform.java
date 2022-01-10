package com.example.myguitartuner.logic;

import static java.lang.Math.*;


/**
 * Class in charge of calculating the frequencies.
 */
public class FastFourierTransform {

    /**
     * Array of Complex numbers to be transformed with the FFT algorithm.
     */
    private static ComplexNumber[] buffer;

    /**
     * absolute values of the frequencies, transforms the complex numbers into a double
     */
    private static double[] absoluteValues;

    /**
     * Highest Frequency in the buffer Array, to know how close to the actual guitar string frequency it got
     */
    private double highestFrequency;

    /**
     * Constructor for a FastFourierTransform Object. It takes the PCM data array recorded with the TaskThread Class,
     * and transforms its values to a complex number.
     * @param pcmData
     */
    public FastFourierTransform(double[] pcmData) {
        buffer = new ComplexNumber[pcmData.length];
        for (int i = 0; i < pcmData.length; i++) {
            buffer[i] = new ComplexNumber(pcmData[i], 0);
        }
    }

    /**
     * Reversal of the bits
     * @param n
     * @param bits
     * @return
     */
    public static int bitReverse(int n, int bits) {
        int reversedN = n;
        int count = bits - 1;

        n >>= 1;
        while (n > 0) {
            reversedN = (reversedN << 1) | (n & 1);
            count--;
            n >>= 1;
        }

        return ((reversedN << count) & ((1 << bits) - 1));
    }

    /**
     * Actual FastFourierTransform algorithm, to change the complex numbers from time domain to
     * frequency domain.
     */
    static void fft() {
        int bits = (int) (log(buffer.length) / log(2));
        for (int j = 1; j < buffer.length / 2; j++) {

            int swapPos = bitReverse(j, bits);
            ComplexNumber temp = buffer[j];
            buffer[j] = buffer[swapPos];
            buffer[swapPos] = temp;
        }
        for (int N = 2; N <= buffer.length; N <<= 1) {
            for (int i = 0; i < buffer.length; i += N) {
                for (int k = 0; k < N / 2; k++) {

                    int evenIndex = i + k;
                    int oddIndex = i + k + (N / 2);
                    ComplexNumber even = buffer[evenIndex];
                    ComplexNumber odd = buffer[oddIndex];

                    double term = (-2 * PI * k) / (double) N;
                    ComplexNumber exp = (new ComplexNumber(cos(term), sin(term)).mult(odd));

                    buffer[evenIndex] = even.add(exp);
                    buffer[oddIndex] = even.sub(exp);

                }
            }
        }
        absoluteValues = getAbsoluteValues();
    }

    /**
     * Algorithm for calculating the absolute values of the complex numbers and saving them as double.
     * These numbers represent the actual frequency calculated.
     * @return
     */
    private static double[] getAbsoluteValues(){
        double[] result = new double[buffer.length];
        for(int i = 0; i< buffer.length; i++){
            result[i] = sqrt((buffer[i].re * buffer[i].re) + (buffer[i].im * buffer[i].im) );

        }

        return result;
    }

    /**
     * Simple search algorithm to search for the highest value in the absoluteValues array. The result will be used to
     * compare how close the tuning is, compared to the expected value.
     * @return
     */
    public double getHighestFrequency(){
        highestFrequency = absoluteValues[0];
        for(int i =1; i< absoluteValues.length; i++){
            if(highestFrequency < absoluteValues[i]){
                highestFrequency = absoluteValues[i];
            }
        }
        return highestFrequency;
    }
}
