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
    
    public void increaseLength() {
        this.size += (this.values.length + this.values.length / 2);
        double[] newValues = new double[size];
        
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = this.values[i];
        }
        
        this.values = newValues;
    }
    
    public void addValue(double value) {
        if (this.index < this.size) {
            this.values[index] = value;
            this.index++;            
        } else {
            this.increaseLength();
            this.values[index] = value;
            this.index++;
        }
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
        double[] result = new double[this.index];
        
        for (int i = 0; i < this.index; i++) {
            result[i] = this.values[i];
        }
        
        return result;
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
        double SSD = 0;
        
        for (Double value : this.values) {            
            SSD += Math.pow((value - mean), 2);
        }
        return SSD / this.index;
    }
    
    @Override
    public String toString() {
        return Arrays.toString(this.values);
    }
}
