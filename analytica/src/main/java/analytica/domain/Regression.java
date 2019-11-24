package analytica.domain;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Regression {
    
    private SimpleRegression sr;
    
    public Regression() {
        this.sr = new SimpleRegression();
    }
    
    public long getNumberOfValuePairs() {
        return this.sr.getN();
    }
    
    public void addData(double x, double y) {
        this.sr.addData(x, y);
    }
    
}
