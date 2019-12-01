package analytica.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
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
    
    public boolean valuesIsEmpty() {
        return this.index <= 0;
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
        if (this.valuesIsEmpty()) return 0.0;
        
        return this.getSum() / this.index;
    }
    
    public double getMedian() {
        if (this.valuesIsEmpty()) return 0.0;
        
        double[] array = this.getValues();
        Arrays.sort(array);
        int index = (int) Math.floor((this.index - 1) / 2);        
        return array[index];
    }
    
    public double getMode() {
        if (this.valuesIsEmpty()) return  0.0;
        
        double[] array = this.getValues();
        Arrays.sort(array);
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
    
    public double getSum() {
        double sum = 0.0;
        
        for (double value : this.values) {
            sum += value;
        }
        
        return sum;
    }
    
    public double getVariance() {
        if (this.valuesIsEmpty()) return 0.0;
        
        double mean = this.getMean();
        double ssd = 0;
        
        for (int i = 0; i < this.index; i++) {            
            ssd += Math.pow((this.values[i] - mean), 2);
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
        return Arrays.toString(this.getValues());
    }
}
