package analytica.domain;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.List;

public class Statistics {
    DescriptiveStatistics ds;
    
    public Statistics() {
        this.ds = new DescriptiveStatistics();
    }       
    
    public void addValue(double value) {
        ds.addValue(value);
    }
    
    public void addValues(double[] values) {
        for (Double value : values) {
            this.ds.addValue(value);
        }
    }
    
    public void addValues(List<Double> values) {
        for (double value : values) {
            this.ds.addValue(value);
        }
    }
    
    public double[] getValues() {
        return ds.getValues();
    }
    
    public double getMean() {
        return this.ds.getMean();
    }
    
    @Override
    public String toString() {
        return ds.toString();
    }
}
