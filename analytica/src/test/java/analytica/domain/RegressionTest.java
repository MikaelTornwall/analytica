package analytica.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

        assertEquals(1L, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void anArrayOfvaluePairsCanBeAddedForRegression() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};

        regression.addData(data);

        assertEquals(6L, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void modelCanBeCleared() {        
        double[][] data = {{1,10}, {2,12}, {3,14}, {8, 11}, {7, 2}, {9, 19}};
        regression.addData(data);
        regression.clear();
        assertEquals(0L, regression.getNumberOfValuePairs());
    }
    
    @Test
    public void invalidArrayOfValuePairsIsNotAdded() {
        double[][] data = {{1,10}, {2,12}, {3,14}, {8}, {7, 2}, {9, 19}};
        regression.addData(data);
        assertEquals(0L, regression.getNumberOfValuePairs());
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
}
