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
}
