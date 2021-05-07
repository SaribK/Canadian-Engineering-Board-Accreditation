/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Tests for the AttributeT ADT class
 */

package src;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class TestAttributeT
{
    private AttributeT ProbAnalysis;
    private AttributeT Design;
    private AttributeT Duplicates;
    private AttributeT Empty;

    @Before
    public void setUp()
    {
        ProbAnalysis = new AttributeT("Problem Analysis", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund});
        Design = new AttributeT("Design", new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                IndicatorT.openEnded, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.standards});
        Duplicates = new AttributeT("Duplicates", new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund,
                IndicatorT.suitableFund, IndicatorT.assumpt, IndicatorT.assumpt, IndicatorT.suitableFund});
        Empty = new AttributeT("", new IndicatorT[]{});
    }

    @After
    public void tearDown()
    {
        ProbAnalysis = null;
        Design = null;
        Duplicates = null;
        Empty = null;
    }

    // simple cases
    @Test
    public void test_getName1()
    {
        assertEquals("Problem Analysis", ProbAnalysis.getName());
    }

    @Test
    public void test_getName2()
    {
        assertEquals("Design", Design.getName());
    }

    // edge case for string
    @Test
    public void test_getName3()
    {
        assertEquals("", Empty.getName());
    }

    @Test
    public void test_getIndicators1()
    {
        // first ordering for array (boundary case)
        assertTrue(arrayEquality(ProbAnalysis.getIndicators(), new IndicatorT[] {IndicatorT.suitableFund, IndicatorT.assumpt}));
    }

    @Test
    public void test_getIndicators2()
    {
        // second ordering for same array (boundary case)
        assertTrue(arrayEquality(ProbAnalysis.getIndicators(), new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund}));
    }

    @Test
    public void test_getIndicators3()
    {
        // mixed up order (edge case since it is using a big list)
        assertTrue(arrayEquality(Design.getIndicators(), new IndicatorT[] {IndicatorT.standards, IndicatorT.desPrinciples,
                IndicatorT.desProcess, IndicatorT.ideaGeneration,
                IndicatorT.healthSafety, IndicatorT.openEnded}));
    }

    @Test
    public void test_getIndicators4()
    {
        // edge case (no indicators)
        assertTrue(arrayEquality(Empty.getIndicators(), new IndicatorT[] {}));
    }

    @Test
    public void test_duplicates()
    {
        // check if duplicates were removed initialized (edge case)
        assertTrue(arrayEquality(Duplicates.getIndicators(), new IndicatorT[] {IndicatorT.assumpt, IndicatorT.suitableFund}));
    }

    public static boolean arrayEquality(Object[] a1, Object[] a2)
    {
        // must be equal length
        if (a1.length != a2.length)
        {
            return false;
        }
        List<Object> list1 = Arrays.asList(a1);
        List<Object> list2 = Arrays.asList(a2);
        for (Object item : list1)
        {
            // each element of list1 should be in list2
            if (!list2.contains(item))
                return false;
        }
        return true;
    }
}
