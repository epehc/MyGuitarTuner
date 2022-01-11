package com.example.myguitartuner.Model;

/**
 * Enum StringFrequency that has all required values for comparing the frequencies
 * @source https://en.wikipedia.org/wiki/Guitar_tunings
 * @source https://blog.sonicbids.com/eq-tips-for-eliminating-background-noise-from-your-mixes-a-table-of-note-frequencies
 */
public enum StringFrequency {
    /**
     * First String E_4
     */
    e_4(329.63,  19.6, 18.5),
    /**
     * Second String B
     */
    b(246.94, 14.7, 13.8),
    /**
     * Third String G
     */
    g(196, 11.7, 11),
    /**
     * Fourth String D
     */
    d(146.83, 8.8, 8.2),
    /**
     * Fifth String A
     */
    a(110, 6.5, 6.2),
    /**
     * Sixth String E_2
     */
    e_2(82.41, 4.9, 4.63);

    /**
     * The exact frequency a string should have (in Hz)
     */
    private double targetFrequency;

    /**
     * The positive tolerance (in Hz)
     */
    private double plusFrequency;

    /**
     * The negative tolerance (in Hz)
     */
    private double minusFrequency;

    /**
     * Getter for the targeted frequency
     * @return
     */
    public double getTargetFrequency() {
        return targetFrequency;
    }

    /**
     * Getter for the tolerated in range plus frequency
     * @return
     */
    public double getPlusFrequency() {
        return targetFrequency + plusFrequency;
    }

    /**
     * Getter for the tolerated in range minus frequency.
     * @return
     */
    public double getMinusFrequency() {
        return targetFrequency - minusFrequency;
    }

    //The following Methods are useful for updating the elements in the GUI.


    /**
     * The tuning is way too low for the string.
     * @param frequency the current frequency
     * @return if the frequency is way too low
     */
    public boolean getWayBelowFrequency(double frequency){
        double value = frequency / getMinusFrequency();
        return frequency < getMinusFrequency() && value < 0.5;

    }

    /**
     * The tuning is a bit low, but it's getting close to the target frequency
     * @param frequency the current frequency
     * @return if the frequency is just below the target range
     */
    public boolean getBelowFrequency(double frequency){
        double value = frequency / getMinusFrequency();
        return frequency < getMinusFrequency() && value >= 0.5;
    }

    /**
     * The target range for a string. This is the ideal tuning
     * @param frequency the current frequency
     * @return if the frequency is within the ideal range
     */
    public boolean getTargetRange(double frequency){
        return frequency >= getMinusFrequency() && frequency <= getPlusFrequency();
    }

    /**
     * The tuning is a bit high, but it's close to the ideal tuning
     * @param frequency the current frequency
     * @return if the frequency is a bit high
     */
    public boolean getAboveFrequency(double frequency){
        double value = frequency / getPlusFrequency();
        return frequency > getPlusFrequency() && value <= 1.5;
    }

    /**
     * The tuning is way too high
     * @param frequency the current frequency
     * @returnn if the frequency is too high
     */
    public boolean getWayAboveFrequency(double frequency){
        double value = frequency / getPlusFrequency();
        return frequency > getPlusFrequency() && value > 1.5;
    }

    /**
     * Constructor for the Enum with all necessary parameters
     * @param targetFrequency the exact frequency a string should have
     * @param plusFrequency the tolerated positive tolerance
     * @param minusFrequency the tolerated negative tolerance
     */
    StringFrequency(double targetFrequency, double plusFrequency, double minusFrequency) {
        this.targetFrequency = targetFrequency;
        this.plusFrequency = plusFrequency;
        this.minusFrequency = minusFrequency;
    }
}
