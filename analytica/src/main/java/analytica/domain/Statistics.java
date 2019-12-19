package analytica.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Statistics class contains statistical methods for data analysis
 * 
 * @author Mikael Törnwall
 */

public class Statistics {    
    double[] values;    
    int index;    
    int size;
    
    public Statistics() {
        this.values = new double[10];
        this.size = 10;
        this.index = 0;        
    }       
    
    /**
    * Method tells how many values are within the statistics model
    *
    * @return number of values within the model
    */
    
    public long getNumberOfValues() {
        return this.index;
    }
    
    /**
    * Method tells if model is empty
    *
    * @return true if model is empty, false otherwise
    */
    
    public boolean valuesIsEmpty() {
        return this.index <= 0;
    }
    
    /**
    * Method increases the length of the values array    
    */
    
    public void increaseLength() {
        this.size = (this.size + this.size / 2);
        double[] array = new double[size];
        
        for (int i = 0; i < this.values.length; i++) {
            array[i] = this.values[i];
        }
        
        this.values = array;
    }
    
    /**
     * Method allows adding values to the model
     * 
     * @param value that is added to the model
     */
    
    public void addValue(double value) {
        if (this.index >= this.size) {
            this.increaseLength();
        }
        
        this.values[index] = value;
        this.index++;                  
    }
    
    /**
     * Method allows adding an array of values to the model
     * 
     * @param values is an array of values that will be added to the model
     */
    
    public void addValues(double[] values) {
        for (double value : values) {
            this.addValue(value);
        }
    }
    
    /**
     * Method allows adding a list of values to the model
     * 
     * @param values is a list of values that will be added to the model
     */
    
    public void addValues(List<Double> values) {
        for (double value : values) {
            this.addValue(value);
        }
    }
    
    /**
     * Method allows getting the values from the model
     * 
     * @return an array of model values
     */
    
    public double[] getValues() {
        double[] array = new double[this.index];
        
        for (int i = 0; i < this.index; i++) {
            array[i] = this.values[i];
        }
        
        return array;
    }
    
    /**
     * Method allows getting the sorted values from the model
     * 
     * @return an array of sorted model values
     */
    
    public double[] getSortedValues() {
        double[] array = this.getValues();
        Arrays.sort(array);
        return array;
    }
    
    /**
     * Method calculates the mean of the model values
     * 
     * @return mean value of model values as a decimal number
     */
    
    public double getMean() {  
        if (this.valuesIsEmpty()) {
            return 0.0;
        }
        
        return this.getSum() / this.index;
    }
    
    /**
     * Method calculates the median of the model values
     * 
     * @return median value of model values as a decimal number
     */
    
    public double getMedian() {
        if (this.valuesIsEmpty()) {
            return 0.0;
        }
        
        double[] array = this.getValues();
        Arrays.sort(array);
        int index = (int) Math.floor((this.index - 1) / 2);        
        return array[index];
    }   
    
    /**
     * Method calculates the mode of the model values
     * 
     * @return mode value of model values as a decimal number
     */
    
    public double getMode() {
        if (this.valuesIsEmpty()) {
            return  0.0;
        }
        
        double[] array = this.getSortedValues();
        
        double result = array[0];
        int max = 1;
        int current = 1;
        
        for (int i = 1; i < this.index; i++) {
            if (array[i] == array[i - 1]) {
                current++;
            } else {
                if (current > max) {
                    max = current;
                    result = array[i - 1];
                }
                current = 1;
            }
        }
        
        if (current > max) {
            max = current;
            result = array[this.index - 1];
        }
        
        return result;                
    }
    
    /**
     * Method calculates the sum of the model values
     * 
     * @return sum of model values as a decimal number
     */
    
    public double getSum() {
        double sum = 0.0;
        
        for (double value : this.values) {
            sum += value;
        }
        
        return sum;
    }        
    
    /**
     * Method calculates the variance of the model values
     * 
     * @return variance of model values as a decimal number
     */
    
    public double getVariance() {
        if (this.valuesIsEmpty()) {
            return 0.0;
        }
        
        double mean = this.getMean();
        double ssd = 0;
        
        for (int i = 0; i < this.index; i++) {            
            ssd += Math.pow((this.values[i] - mean), 2);
        }
        return ssd / this.index;
    }
    
    /**
     * Method calculates the standard deviation of the model values
     * 
     * @return standard deviation of model values as a decimal number
     */
    
    public double getStandardDeviation() {
        return Math.sqrt(this.getVariance());
    }
    
    /**
     * Method replaces the values array with an empty array
     */
    
    public void clear() {        
        this.values = new double[10];
        this.size = 10;
        this.index = 0;
    }
        
    @Override
    public String toString() {
        return Arrays.toString(this.getValues());
    }
}
