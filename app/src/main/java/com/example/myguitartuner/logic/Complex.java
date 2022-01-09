package com.example.myguitartuner.logic;

/**
 * Complex number class, representing the behavior of a complex number
 */
public class Complex {

    /**
     * Real part of the complex number
     */
    public final double re;

    /**
     * Imaginary part of the complex number
     */
    public final double im;

    /**
     * Empty Constructor
     */
    public Complex() {
        this(0, 0);
    }

    /**
     * Constructor for a  complex number
     */
    public Complex(double r, double i) {
        re = r;
        im = i;
    }

    /**
     * Simple compareTo method
     * @param o Complex number to be compared to.
     * @return true if both the real part and imaginary part of the complex number calling the function are
     * greater than those of the o complex number, or false if oterwise.
     */
    public boolean compareTo(Complex o){

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
    public Complex add(Complex b) {
        return new Complex(this.re + b.re, this.im + b.im);
    }

    /**
     * Substraction of complex numbers
     * @param b Complex number to substract
     * @return a new Complex number with both numbers substracted
     */
    public Complex sub(Complex b) {
        return new Complex(this.re - b.re, this.im - b.im);
    }

    /**
     * Multiplication of complex numbers
     * @param b number to multiply with
     * @return a new complex number with the result of the multiplication
     */
    public Complex mult(Complex b) {
        return new Complex(this.re * b.re - this.im * b.im,
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
