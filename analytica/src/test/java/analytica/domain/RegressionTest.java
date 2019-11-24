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
    public void modelCanBeCleared() {        
        assertEquals(0, regression.getNumberOfValuePairs());
    }
}
