package com.example.myguitartuner.Model;

/**
 * Complex number class, representing the behavior of a complex number
 */
public class ComplexNumber {

    /**
     * Real part of the complex number
     */
    private final double re;

    /**
     * Imaginary part of the complex number
     */
    private final double im;

    /**
     * Empty Constructor
     */
    public ComplexNumber() {
        this(0, 0);
    }

    /**
     * Constructor for a  complex number
     */
    public ComplexNumber(double r, double i) {
        re = r;
        im = i;
    }

    /**
     * Getter for real part of the ComplexNumber
     * @return
     */
    public double getRe() {
        return re;
    }

    /**
     * Getter for imaginary part of the ComplexNumber
     * @return
     */
    public double getIm() {
        return im;
    }

    /**
     * Simple compareTo method
     * @param o Complex number to be compared to.
     * @return true if both the real part and imaginary part of the complex number calling the function are
     * greater than those of the o complex number, or false if oterwise.
     */
    public boolean compareTo(ComplexNumber o){

        if(this.re > o.re && this.im > o.im){
            return true;
        }
        return false;
    }

    /**
     * Add two complex numbers
     * @param b Complex number to add
     * @return a new Complex number
     */
    public ComplexNumber add(ComplexNumber b) {
        return new ComplexNumber(this.re + b.re, this.im + b.im);
    }

    /**
     * Substraction of complex numbers
     * @param b Complex number to substract
     * @return a new Complex number with both numbers substracted
     */
    public ComplexNumber sub(ComplexNumber b) {
        return new ComplexNumber(this.re - b.re, this.im - b.im);
    }

    /**
     * Multiplication of complex numbers
     * @param b number to multiply with
     * @return a new complex number with the result of the multiplication
     */
    public ComplexNumber mult(ComplexNumber b) {
        return new ComplexNumber(this.re * b.re - this.im * b.im,
                this.re * b.im + this.im * b.re);
    }

    /**
     * Simple toString Method.
     * @return
     */
    @Override
    public String toString() {
        return String.format("(%f,%f)", re, im);
    }
}
