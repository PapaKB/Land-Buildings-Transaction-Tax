/**
 * A class to represent the different tax bands in LBTT
 */
public class Band {
    private int keyBoundary;
    private int percentage;

    /**
     * Instantiates a new Band.
     *
     * @param keyBoundary the key boundary
     * @param percentage  the percentage
     */
    public Band(int keyBoundary, int percentage) {
        this.keyBoundary = keyBoundary; //this will be the end of the property value bracket except for the last band where this will be the start
        this.percentage = percentage;
    }

    /**
     * Gets key boundary. For all but the last band, the key boundary is the highest value in the band
     *
     * @return the key boundary
     */
    public int getKeyBoundary() {
        return this.keyBoundary;
    }

    /**
     * Gets percentage of the taxable sum.
     *
     * @return the percentage
     */
    public int getPercentage() {
        return this.percentage;
    }
}