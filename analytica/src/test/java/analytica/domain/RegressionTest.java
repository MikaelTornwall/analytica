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
}
