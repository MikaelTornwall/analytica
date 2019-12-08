package analytica.domain;

public class Regression {
        
    private double[] xValues;
    private double[] yValues;
    private int index;
    private int size;
    Statistics xStatistics;
    Statistics yStatistics;
    
    public Regression() {
        this.xValues = new double[10];
        this.yValues = new double[10];
        this.index = 0;
        this.size = 10;
        this.xStatistics = new Statistics();
        this.yStatistics = new Statistics();
    }
    
    public void increaseLength() {
        this.size = this.size + this.size / 2;
        double[] xArray = new double[this.size];
        double[] yArray = new double[this.size];

        for (int i = 0; i < this.index; i++) {
            xArray[i] = this.xValues[i];
            yArray[i] = this.yValues[i];
        }

        this.xValues = xArray;
        this.yValues = yArray;        
    }
    
    public void addData(double x, double y) {
        if (this.index >= this.size) {
            this.increaseLength();
        }
        
        this.xValues[index] = x;
        this.yValues[index] = y;
        this.index++;
        this.xStatistics.addValue(x);
        this.yStatistics.addValue(y);
    }
    
    public boolean checkDataArrayStructure(double[][] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].length != 2) {
                return false;
            }
        }
        return true;
    }
    
    public void addData(double[][] data) {   
        if (!this.checkDataArrayStructure(data)) {
            return;
        }
        
        for (int i = 0; i < data.length; i++) {
            this.addData(data[i][0], data[i][1]);
        }                
    }
    
    public long getNumberOfValuePairs() {
        return this.index;
    }    
    
    public double getCovariance() {                
        double xMean = xStatistics.getMean();
        double yMean = yStatistics.getMean();
        double sumOfProducts = 0;
        
        for (int i = 0; i < this.index; i++) {
            sumOfProducts += ((xValues[i] - xMean) * (yValues[i] - yMean));
        }
        
        return sumOfProducts / index;        
    }
    
    public double getCorrelation() {
        double xSd = xStatistics.getStandardDeviation();
        double ySd = yStatistics.getStandardDeviation();
        
        return this.getCovariance() / (xSd * ySd);
    }
    
    public double getSlope() {        
        double covariance = this.getCovariance();
        double variance = this.xStatistics.getVariance();

        return covariance / variance;
    }
    
    public double getIntercept() {        
        return yStatistics.getMean() - (this.getSlope() * xStatistics.getMean());
    }

    public double predict(double x) {
        return this.getIntercept() + this.getSlope() * x;
    }
    
    public void clear() {
        this.xValues = new double[10];
        this.yValues = new double[10];
        this.size = 10;
        this.index = 0;
    }
}
