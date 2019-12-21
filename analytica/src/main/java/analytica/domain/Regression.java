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
    
    /**
     * Method increases the length of xValues and yValues arrays
     *   
     */
    
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
    
    /**
     * Method returns the value pairs within the model
     *   
     * @return two-dimensional array of double values
     */
    
    public double[][] getValuePairs() {
        double data[][] = new double[this.index][2];
        for (int i = 0; i < this.index; i++) {
            data[i][0] = this.xValues[i];
            data[i][1] = this.yValues[i];
        }
        
        return data;
    }
    
    /**
     * Method adds a value pair into the model
     *   
     * @param x is a double value
     * @param y is a double value
     */
    
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
    
    /**
     * Method checks that the array structure given as a parameter is of correct format
     * 
     * @param data is a n x 2-dimensional array containing value pairs
     * @return true if array is n x 2 dimensional, false otherwise 
     */
    
    public boolean checkDataArrayStructure(double[][] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].length != 2) {
                return false;
            }
        }
        return true;
    }        
    
    /**
     * Method adds an array of value pairs into the model
     *       
     * @param data is a n x 2 dimensional array containing value pairs
     */
    
    public void addData(double[][] data) {   
        if (!this.checkDataArrayStructure(data)) {
            return;
        }
        
        for (int i = 0; i < data.length; i++) {
            this.addData(data[i][0], data[i][1]);
        }                
    }
    
    /**
     * Method returns the number of value pairs within the model
     *   
     * @return integer value
     */
    
    public int getNumberOfValuePairs() {
        return this.index;
    }    
    
    /**
     * Method calculates the product of a given value pair
     *   
     * @return double value
     */
    
    public double getProductOfValuePair(double x, double y) {
        return x * y;
    }
    
    /**
     * Method calculates the sum of products of all value pairs within the model
     *   
     * @return double value
     */
    
    public double getProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {            
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getSum();
    }
    
    /**
     * Method calculates the mean of products of all value pairs within the model
     *   
     * @return double value
     */
    
    public double getMeanOfProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getMean();
    }
    
    /**
     * Method calculates the median of products of all value pairs within the model
     *   
     * @return double value
     */
    
    public double getMedianOfProductOfValuePairs() {
        for (int i = 0; i < this.index; i++) {
            this.statistics.addValue(this.getProductOfValuePair(xValues[i], yValues[i]));
        }
        
        return this.statistics.getMedian();
    }        
    
    /**
     * Method calculates the rate of sum of x values out of the sum of all values
     *   
     * @return double value
     */
    
    public double getXRate() {
        double xSum = this.xStatistics.getSum();
        double totalSum = this.xStatistics.getSum() + this.yStatistics.getSum();
        return xSum / totalSum;
    }
    
    /**
     * Method calculates the rate of sum of y values out of the sum of all values
     *   
     * @return double value
     */
    
    public double getYRate() {
        double ySum = this.yStatistics.getSum();
        double totalSum = this.xStatistics.getSum() + this.yStatistics.getSum();
        return ySum / totalSum;
    }
    
    /**
     * Method calculates the covariance between x and y values
     *   
     * @return double value
     */
    
    public double getCovariance() {                
        double xMean = xStatistics.getMean();
        double yMean = yStatistics.getMean();
        double sumOfProducts = 0;
        
        for (int i = 0; i < this.index; i++) {
            sumOfProducts += ((xValues[i] - xMean) * (yValues[i] - yMean));
        }
        
        return sumOfProducts / index;        
    }
    
    /**
     * Method calculates the correlation between x and y values
     *   
     * @return double value
     */
    
    public double getCorrelation() {
        double xSd = xStatistics.getStandardDeviation();
        double ySd = yStatistics.getStandardDeviation();
        
        return this.getCovariance() / (xSd * ySd);
    }
    
    /**
     * Method calculates the slope value for linear regression model
     *   
     * @return double value
     */
    
    public double getSlope() {        
        double covariance = this.getCovariance();
        double variance = this.xStatistics.getVariance();

        return covariance / variance;
    }
    
    /**
     * Method calculates the intercept value for linear regression model
     *   
     * @return double value
     */
    
    public double getIntercept() {        
        return yStatistics.getMean() - (this.getSlope() * xStatistics.getMean());
    }
    
    /**
     * Method calculates prediction of y given x by linear regression function
     *   
     * @param x is a double value
     * @return double value
     */

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
