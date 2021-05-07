/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Tests for the LOsT ADT class
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

public class TestLOsT
{
    private LOsT LO1;
    private LOsT LO2;
    private LOsT LO3;

    @Before
    public void setUp()
    {
        Norm.setNorms(false, false, false);
        LO1 = new LOsT("Unit 1: Parabolas", 5, 12, 47, 23);
        LO2 = new LOsT("Unit 4: Kinematics", 35, 13, 27, 2);
        LO3 = new LOsT("Edge Case", 100000, 100000, 100000, 100000);
    }

    @After
    public void tearDown()
    {
        LO1 = null;
        LO2 = null;
    }

    // normal cases
    @Test
    public void test_getName()
    {
        assertEquals(LO1.getName(), "Unit 1: Parabolas");
    }

    @Test
    public void test_equals1()
    {
        assertEquals(LO1, LO1);
    }

    @Test
    public void test_equals2()
    {
        assertEquals(LO1, new LOsT("Unit 1: Parabolas", 12, 4, 3, 5));
    }

    // normal case
    @Test
    public void test_measures1()
    {
        assertTrue(TestLOsT.doubleArrayEquality(LO1.measures(), new double[]{5.0, 12.0, 47.0, 23.0}));
    }

    // set NLOs to true so that norm is being used
    @Test
    public void test_measures2()
    {
        Norm.setNLOs(true);
        double[] actual = LO1.measures();
        double[] expected = new double[]{0.057471264, 0.137931034, 0.540229885, 0.264367816};
        boolean result = doubleArrayEquality(actual, expected);
        assertTrue(result);
    }

    // set NLOs true
    @Test
    public void test_measures3()
    {
        Norm.setNLOs(true);
        double[] actual = LO2.measures();
        double[] expected = new double[]{0.45454545454545, 0.168831168, 0.35064935, 0.025974025};
        boolean result = doubleArrayEquality(actual, expected);
        assertTrue(result);
    }

    // edge case (LO3 has large values in the list)
    @Test
    public void test_measures4()
    {
        assertTrue(TestLOsT.doubleArrayEquality(LO3.measures(), new double[]{100000.0, 100000.0, 100000.0, 100000.0}));
    }

    // equal 4 values divide up the norm equally
    @Test
    public void test_measures5()
    {
        Norm.setNLOs(true);
        double[] actual = LO3.measures();
        double[] expected = new double[]{0.25, 0.25, 0.25, 0.25};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // check exceptions for the unsupported measures functions
    @Test(expected = IllegalArgumentException.class)
    public void test_exceptionNegatives()
    {
        LOsT temp = new LOsT("Periodic Tables", 4, 23, 5, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_exceptionZeroes()
    {
        LOsT temp = new LOsT("Periodic Tables", 0, 0, 0, 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_exceptionMeasuresIndicator()
    {
        LO1.measures(IndicatorT.math);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_exceptionMeasuresAttribute()
    {
        LO1.measures(new AttributeT("Math", new IndicatorT[]{IndicatorT.math}));
    }

    public static boolean doubleArrayEquality(double[] a, double[] b)
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = a[i] - b[i];
            if (!(Math.abs(a[i]) < 0.0001))
            {
                return false;
            }
        }
        return true;
    }
}
