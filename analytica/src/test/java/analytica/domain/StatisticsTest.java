package analytica.domain;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {       
    
    private Statistics statistics;        
    
    @Before
    public void setUp() {
        this.statistics = new Statistics();
    }   
    
    @Test
    public void valueCanBeAdded() {
        this.statistics.addValue(5.0);
        double[] values = new double[1];
        values[0] = 5.0;
        double[] response = this.statistics.getValues();                        
         
        assertArrayEquals(response, values, 0.0f);                
    }

    @Test
    public void valuesCanBeAddedWithList() {
        List<Double> list = new ArrayList<>();
        double[] array = new double[10];
        
        for (int i = 1; i <= 10; i++) {
            list.add((double) i);
            array[i - 1] = i;
        }
        
        this.statistics.addValues(list);
        double[] response = this.statistics.getValues();
        
        assertArrayEquals(response, array, 0.0f);
    }
    
    @Test
    public void valuesCanBeAddedWithArray() {                       
        double[] array = new double[10];
        
        for (int i = 1; i <= 10; i++) {            
            array[i - 1] = i;
        }
        
        this.statistics.addValues(array);
        double[] response = this.statistics.getValues();
        
        assertArrayEquals(response, array, 0.0f);
    }
    
    @Test
    public void numberOfAddedValuesIsCorrect() {
        double[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        this.statistics.addValues(array);
        assertEquals(9L, this.statistics.getNumberOfValues());
    }
    
    @Test
    public void sumIsCorrect() {
        int n = 10;
        double[] array = new double[n];
        double sum = 0.0;
                
        for (int i = 1; i <= 10; i++) {
            array[i - 1] = i;
            sum += i;
        }
        
        this.statistics.addValues(array);
        
        assertEquals(sum, this.statistics.getSum(), 0.0f);
    }
    
    @Test
    public void meanIsCorrect() {
        int n = 10;
        double[] array = new double[n];
        double sum = 0.0;
                
        for (int i = 1; i <= 10; i++) {
            array[i - 1] = i;
            sum += i;
        }
        
        double mean = sum / n;
        this.statistics.addValues(array);
        
        assertEquals(mean, this.statistics.getMean(), 0.0f);
    }
    
    @Test
    public void medianIsCorrect() {
        double[] array = {1, 3, 2, 9, 7, 3, 5, 10, 9, 6};
        this.statistics.addValues(array);
        assertEquals(5, this.statistics.getMedian(), 0.0f);
    }        
    
    @Test
    public void modeIsCorrect() {
        double[] array = {1, 2, 8, 1, 3, 4, 2, 10, 2};
        this.statistics.addValues(array);
        assertEquals(2.0, this.statistics.getMode(), 0.0f);
    }
    
    @Test
    public void varianceIsCorrect() {
        double[] array = {2, 7, 8, 9, 10, 10, 11, 11, 12, 18};        
        double variance = 14.760000000000002;                
        this.statistics.addValues(array);        
        assertEquals(variance, this.statistics.getVariance(), 0.0f);
    }
    
    @Test
    public void standardDeviationIsCorrect() {
        double[] array = {2, 7, 8, 9, 10, 10, 11, 11, 12, 18};        
        double sd = Math.sqrt(14.760000000000002);                
        this.statistics.addValues(array);        
        assertEquals(sd, this.statistics.getStandardDeviation(), 0.0f);
    }
    
    @Test 
    public void sumIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getSum(), 0.0f);
    }
    
    @Test
    public void meanIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getMean(), 0.0f);
    }
    
    @Test
    public void medianIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getMedian(), 0.0f);
    }
    
    @Test
    public void modeIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getMode(), 0.0f); 
    }
    
    @Test
    public void varianceIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getVariance(), 0.0f);
    }
    
    @Test
    public void standardDeviationIsCorrectWithNoValues() {
        assertEquals(0.0, this.statistics.getStandardDeviation(), 0.0f);
    }    
    
    @Test
    public void modelCanBeCleared() {
        double[] array = {2, 7, 8, 9, 10, 10, 11, 11, 12, 18};
        this.statistics.addValues(array);
        this.statistics.clear();
        assertEquals(0L, this.statistics.getNumberOfValues());
    }
}
