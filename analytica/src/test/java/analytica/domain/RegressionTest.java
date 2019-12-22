package analytica.domain;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegressionTest {
       
    private Regression regression;

    @Before
    public void setUp() {
        this.regression = new Regression();
    }
    
    public void initializeRegression() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
    }

    @Test
    public void valuePairCanBeAddedForRegression() {
        int x = 2;
        int y = 9;

        regression.addData(x, y);

        assertEquals(1, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void anArrayOfvaluePairsCanBeAddedForRegression() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);

        assertEquals(6, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void modelCanBeCleared() {        
        this.initializeRegression();
        regression.clear();
        assertEquals(0, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void invalidArrayOfValuePairsIsNotAdded() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(0, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void numberOfValuePairsIsCorrect() {
        this.initializeRegression();
        assertEquals(6, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void correctValuePairsAreReturned() {
        double[][] data = {{1, 2}, {3, 4}, {5, 6}};
        regression.addData(data);
        assertTrue(Arrays.deepEquals(data, regression.getValuePairs()));
    }
    
    @Test
    public void xRateIsCorrect() {
        this.initializeRegression();
        assertEquals(0.30612244897959184, regression.getXRate(), 0.0f);
    }
    
    @Test
    public void yRateIsCorrect() {
        this.initializeRegression();
        assertEquals(0.6938775510204082, regression.getYRate(), 0.0f);
    }
    
    @Test
    public void productOfValuePairIsCorrect() {
        assertEquals(8, regression.getProductOfValuePair(2, 4), 0.0f);
    }
    
    @Test
    public void productOfValuePairsIsCorrect() {
        this.initializeRegression();
        assertEquals(349.0, regression.getProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void meanOfProductOfValuePairsIsCorrect() {
        this.initializeRegression();
        assertEquals(58.166666666666664, regression.getMeanOfProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void medianOfProductOfValuePairsIsCorrect() {
        this.initializeRegression();
        assertEquals(24.0, regression.getMedianOfProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void covarianceIsCorrect() {
        this.initializeRegression();
        assertEquals(1.5, regression.getCovariance(), 0.0f);
    }       
    
    @Test
    public void correlationIsCorrect() {  
        this.initializeRegression();
        assertEquals(0.09481917704584825, regression.getCorrelation(), 0.0f);
    }
    
    @Test
    public void slopeIsCorrect() {     
        this.initializeRegression();
        assertEquals(0.15517241379310345, regression.getSlope(), 0.0f);
    }
    
    @Test
    public void interceptIsCorrect() {   
        this.initializeRegression();
        assertEquals(10.557471264367816, regression.getIntercept(), 0.0f);
    }
    
    @Test
    public void predictIsCorrect() {
        this.initializeRegression();        
        assertEquals(11.333333333333334, regression.predict(5), 0.0f);
    }
    
    @Test
    public void predictionIsZeroWhenItWouldBeBelowZero() {
        double[][] data = {{10, 1}, {20, 7}, {30, 20}};
        regression.addData(data);
        assertEquals(0.0, regression.predict(1), 0.0f);
    }
}
