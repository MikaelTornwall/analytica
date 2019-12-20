package analytica.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegressionTest {
       
    private Regression regression;

    @Before
    public void setUp() {
        this.regression = new Regression();
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
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
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
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(6, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void xRateIsCorrect() {
        double[][] data = {{5, 10}, {2, 4}, {3, 6}, {4, 8}, {1, 2}};
        regression.addData(data);
        assertEquals(0.3333333333333333, regression.getXRate(), 0.0f);
    }
    
    @Test
    public void yRateIsCorrect() {
        double[][] data = {{5, 10}, {2, 4}, {3, 6}, {4, 8}, {1, 2}};
        regression.addData(data);
        assertEquals(0.6666666666666666, regression.getYRate(), 0.0f);
    }
    
    @Test
    public void productOfValuePairIsCorrect() {
        assertEquals(8, regression.getProductOfValuePair(2, 4), 0.0f);
    }
    
    @Test
    public void productOfValuePairsIsCorrect() {
        double[][] data = {{1, 2}, {2, 3}, {3, 4}};
        regression.addData(data);
        assertEquals(20, regression.getProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void meanOfProductOfValuePairsIsCorrect() {
        double[][] data = {{5, 10}, {2, 4}, {3, 6}, {4, 8}, {1, 2}};
        regression.addData(data);
        assertEquals(22.0, regression.getMeanOfProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void medianOfProductOfValuePairsIsCorrect() {
        double[][] data = {{5, 10}, {2, 4}, {3, 6}, {4, 8}, {1, 2}};
        regression.addData(data);
        assertEquals(18.0, regression.getMedianOfProductOfValuePairs(), 0.0f);
    }
    
    @Test
    public void covarianceIsCorrect() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(1.5, regression.getCovariance(), 0.0f);
    }       
    
    @Test
    public void correlationIsCorrect() {
        double[][] data = {{1,2}, {2, 3}, {3, 7}, {4, 7}, {6, 2}, {9, 9}};
        regression.addData(data);
        assertEquals(0.5632097394614726, regression.getCorrelation(), 0.0f);
    }
    
    @Test
    public void slopeIsCorrect() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(0.15517241379310345, regression.getSlope(), 0.0f);
    }
    
    @Test
    public void interceptIsCorrect() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(10.557471264367816, regression.getIntercept(), 0.0f);
    }
    
    @Test
    public void predictIsCorrect() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(11.333333333333334, regression.predict(5), 0.0f);
    }
}
