package analytica.domain;

public class Regression {
        
    private double[] xValues;
    private double[] yValues;
    private int index;
    private int size;
    Statistics xStatistics;
    Statistics yStatistics;
    Statistics statistics;
    
    public Regression() {
        this.xValues = new double[10];
        this.yValues = new double[10];
        this.index = 0;
        this.size = 10;
        this.xStatistics = new Statistics();
        this.yStatistics = new Statistics();
        this.statistics = new Statistics();
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
    
    public double[][] getValuePairs() {
        double data[][] = new double[this.index][2];
        for (int i = 0; i < this.index; i++) {
            data[i][0] = this.xValues[i];
            data[i][1] = this.yValues[i];
        }
        
        return data;
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
    
    public int getNumberOfValuePairs() {
        return this.index;
    }    
    
    public double getProductOfValuePair(double x, double y) {
        return x * y;
    }
    
    public double getProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {            
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getSum();
    }
    
    public double getMeanOfProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getMean();
    }
    
    public double getMedianOfProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getMedian();
    }        
    
    public double getXRate() {
        double xSum = this.xStatistics.getSum();
        double totalSum = this.xStatistics.getSum() + this.yStatistics.getSum();
        return xSum / totalSum;
    }
    
    public double getYRate() {
        double ySum = this.yStatistics.getSum();
        double totalSum = this.xStatistics.getSum() + this.yStatistics.getSum();
        return ySum / totalSum;
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
        double prediction = this.getIntercept() + this.getSlope() * x;
        
        if (prediction < 0) {
            return 0.0;
        }
        
        return prediction;
    }
    
    public void clear() {
        this.xValues = new double[10];
        this.yValues = new double[10];
        this.size = 10;
        this.index = 0;
        this.xStatistics.clear();
        this.yStatistics.clear();
    }
}
