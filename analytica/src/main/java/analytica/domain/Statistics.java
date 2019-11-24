package analytica.domain;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Statistics {    
    double[] values;    
    int index;    
    int size;
    
    public Statistics() {
        this.values = new double[10];
        this.size = 10;
        this.index = 0;        
    }       
    
    public long getNumberOfValues() {
        return this.index;
    }
    
    public void increaseLength() {
        this.size = (this.size + this.size / 2);
        double[] array = new double[size];
        
        for (int i = 0; i < this.values.length; i++) {
            array[i] = this.values[i];
        }
        
        this.values = array;
    }
    
    public void addValue(double value) {
        if (this.index >= this.size) {
            this.increaseLength();
        }
        
        this.values[index] = value;
        this.index++;                  
    }
    
    public void addValues(double[] values) {
        for (double value : values) {
            this.addValue(value);
        }
    }
    
    public void addValues(List<Double> values) {
        for (double value : values) {
            this.addValue(value);
        }
    }
    
    public double[] getValues() {
        double[] array = new double[this.index];
        
        for (int i = 0; i < this.index; i++) {
            array[i] = this.values[i];
        }
        
        return array;
    }
    
    public double getMean() {                        
        return this.getSum() / this.index;
    }
    
    public double getSum() {
        double sum = 0;
        
        for (double value : this.values) {
            sum += value;
        }
        
        return sum;
    }
    
    public double getVariance() {
        double mean = this.getMean();
        double ssd = 0;
        
        for (Double value : this.values) {            
            ssd += Math.pow((value - mean), 2);
        }
        return ssd / this.index;
    }
    
    public double getStandardDeviation() {
        return Math.sqrt(this.getVariance());
    }
    
    public void clear() {        
        this.values = new double[10];
        this.size = 10;
        this.index = 0;
    }
    
    @Override
    public String toString() {
        return Arrays.toString(this.values);
    }
}
