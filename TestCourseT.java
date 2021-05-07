/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Tests for the CourseT ADT class
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

public class TestCourseT
{
    IndicatorT[] CS2ME3_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
            IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
            IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
            IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
            IndicatorT.awarePEO};

    IndicatorT[] CS2XB3_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.assumpt,
            IndicatorT.recogTheory, IndicatorT.modelSelect, IndicatorT.tools,
            IndicatorT.estOutcomes, IndicatorT.openEnded, IndicatorT.ideaGeneration };

    private CourseT CS2ME3;
    private CourseT CS2XB3;

    @Before
    public void setUp()
    {
        Norm.setNorms(false, false, false);
        CS2ME3 = new CourseT("Software Engineering Designs 1", CS2ME3_indicators);
        CS2XB3 = new CourseT("Software Engineering Practice and Experience", CS2XB3_indicators);
        CS2ME3.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles", 35, 13, 27, 2));
        CS2ME3.addLO(IndicatorT.math, new LOsT("Discrete Math", 24, 15, 27, 0));
        CS2ME3.addLO(IndicatorT.math, new LOsT("Linear Algebra", 13, 12, 37, 21));
        CS2ME3.addLO(IndicatorT.math, new LOsT("Fundamentals of Calculus", 0, 25, 67, 18));
        CS2XB3.addLO(IndicatorT.ideaGeneration, new LOsT("Red Black Trees", 5, 12, 47, 23));
        CS2XB3.addLO(IndicatorT.recogTheory, new LOsT("Red Black Trees", 5, 2, 4, 0));
        CS2XB3.addLO(IndicatorT.recogTheory, new LOsT("Binary Search Trees", 5, 12, 47, 23));
        CS2XB3.addLO(IndicatorT.recogTheory, new LOsT("Shortest Path in Weighted Graph", 8, 5, 55, 16));
        CS2XB3.addLO(IndicatorT.recogTheory, new LOsT("Append Runtime", 24, 13, 5, 2));
        CS2XB3.addLO(IndicatorT.recogTheory, new LOsT("Depth First Search", 15, 2, 37, 13));
        CS2XB3.addLO(IndicatorT.math, new LOsT("Excel", 0, 5, 20, 63));
        CS2XB3.addLO(IndicatorT.math, new LOsT("Mean, Median, Mode", 0, 0, 10, 82));
    }

    @After
    public void tearDown()
    {
        CS2ME3 = null;
        CS2XB3 = null;
    }

    // normal cases
    @Test
    public void test_getName1()
    {
        assertEquals("Software Engineering Designs 1", CS2ME3.getName());
    }

    @Test
    public void test_getName2()
    {
        assertEquals("Software Engineering Practice and Experience", CS2XB3.getName());
    }

    @Test
    public void test_getIndicators1()
    {
        assertTrue(TestAttributeT.arrayEquality(CS2ME3.getIndicators(), CS2ME3_indicators));
    }

    @Test
    public void test_getIndicators2()
    {
        assertTrue(TestAttributeT.arrayEquality(CS2XB3.getIndicators(), CS2XB3_indicators));
    }

    @Test
    public void test_getLOs1()
    {
        //automatically tests addLOs as well since this would not work if addLOs did not work
        assertTrue(arrayEqualityLOsT(CS2ME3.getLOs(IndicatorT.desPrinciples),
                new LOsT[]{new LOsT("Software Engineering Principles", 35, 13, 27, 2)}));
    }

    @Test
    public void test_getLOs2()
    {
        assertTrue(arrayEqualityLOsT(CS2XB3.getLOs(IndicatorT.ideaGeneration),
                new LOsT[]{new LOsT("Red Black Trees", 5, 12, 47, 23)}));
    }

    @Test
    public void test_getLOs3()
    {
        assertTrue(TestAttributeT.arrayEquality(CS2ME3.getLOs(IndicatorT.tools),
                new LOsT[]{}));
    }

    // array should be empty after deletion
    @Test
    public void test_delLO1()
    {
        CS2XB3.delLO(IndicatorT.ideaGeneration, new LOsT("Red Black Trees", 5, 12, 47, 23));
        assertTrue(arrayEqualityLOsT(CS2XB3.getLOs(IndicatorT.ideaGeneration),
                new LOsT[]{}));
    }

    // array should be empty after deletion (deletes two elements this time)
    @Test
    public void test_delLO2()
    {
        CS2XB3.delLO(IndicatorT.math, new LOsT("Mean, Median, Mode", 5, 12, 47, 23));
        CS2XB3.delLO(IndicatorT.math, new LOsT("Excel", 5, 12, 47, 23));
        assertTrue(arrayEqualityLOsT(CS2XB3.getLOs(IndicatorT.math),
                new LOsT[]{}));
    }

    @Test
    public void test_member1()
    {
        assertTrue(CS2ME3.member(IndicatorT.desPrinciples, new LOsT[]{new LOsT("Software Engineering Principles", 35, 13, 27, 2)}));
    }

    // member should not be there since it has been deleted
    @Test
    public void test_member2()
    {
    	// boundary case since set becomes empty
        CS2XB3.delLO(IndicatorT.ideaGeneration, new LOsT("Red Black Trees", 5, 12, 47, 23));
        assertFalse(CS2XB3.member(IndicatorT.ideaGeneration, new LOsT[]{new LOsT("Red Black Trees", 5, 12, 47, 23)}));
        CS2XB3.addLO(IndicatorT.ideaGeneration, new LOsT("Red Black Trees", 5, 12, 47, 23));
    }

    // edge case (checking member for a large list)
    @Test
    public void test_member3()
    {
        assertTrue(CS2XB3.member(IndicatorT.recogTheory, new LOsT[]{new LOsT("Red Black Trees", 5, 2, 4, 0),
                new LOsT("Binary Search Trees", 5, 12, 47, 23),
                new LOsT("Shortest Path in Weighted Graph", 8, 5, 55, 16),
                new LOsT("Depth First Search", 15, 2, 37, 13),
                new LOsT("Append Runtime", 24, 13, 5, 2)}));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_exceptionMeasures()
    {
        CS2ME3.measures();
    }

    @Test
    public void test_measuresIndicator1()
    {
        assertTrue(TestLOsT.doubleArrayEquality(CS2ME3.measures(IndicatorT.desPrinciples), (new double[]{35.0, 13.0, 27.0, 2.0})));
    }

    @Test
    public void test_measuresIndicator2()
    {
        CS2ME3.addLO(IndicatorT.desPrinciples, new LOsT("Red Black Trees", 5, 12, 47, 23));
        assertTrue(TestLOsT.doubleArrayEquality(CS2ME3.measures(IndicatorT.desPrinciples), new double[]{40.0, 25.0, 74.0, 25.0}));
    }

    // normal case (NInd false)
    @Test
    public void test_measuresIndicator3()
    {
        assertTrue(TestLOsT.doubleArrayEquality(CS2ME3.measures(IndicatorT.math), new double[]{37.0, 52.0, 131.0, 39.0}));
    }

    // make NInd true
    @Test
    public void test_measuresIndicator4()
    {
        Norm.setNInd(true);
        double[] actual = CS2ME3.measures(IndicatorT.math);
        double[] expected = new double[]{0.142857142, 0.2007722, 0.505791505, 0.15057915};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // indicator with no learning outcomes
    @Test
    public void test_measuresIndicator5()
    {
        assertTrue(TestLOsT.doubleArrayEquality(CS2ME3.measures(IndicatorT.awarePEO), new double[]{0.0, 0.0, 0.0, 0.0}));
    }

    // one indicator (normal case)
    @Test
    public void test_measuresAttribute1()
    {
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration});
        assertTrue(TestLOsT.doubleArrayEquality(CS2XB3.measures(Creativity), new double[]{5.0, 12.0, 47.0, 23.0}));
    }

    // use no indicators
    @Test
    public void test_measuresAttribute2()
    {
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {});
        assertTrue(TestLOsT.doubleArrayEquality(CS2XB3.measures(Creativity), new double[]{0.0, 0.0, 0.0, 0.0}));
    }

    // use multiple indicators in the attribute (lots of indicators)
    @Test
    public void test_measuresAttribute3()
    {
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.recogTheory});
        assertTrue(TestLOsT.doubleArrayEquality(CS2XB3.measures(Creativity), new double[]{62.0, 46.0, 195.0, 77.0}));
    }

    // make NAtt true
    @Test
    public void test_measuresAttribute4()
    {
        Norm.setNAtt(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.recogTheory});
        double[] actual = CS2XB3.measures(Creativity);
        double[] expected = new double[]{0.1631578, 0.12105263, 0.513157, 0.2026315};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make NInd true
    @Test
    public void test_measuresAttribute5()
    {
        Norm.setNInd(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.recogTheory, IndicatorT.ideaGeneration});

        double[] actual = CS2XB3.measures(Creativity);
        double[] expected = new double[]{0.252010513, 0.25397199, 1.045349339, 0.448668157};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make NAtt and NInd true
    @Test
    public void test_measuresAttribute6()
    {
        Norm.setNAtt(true);
        Norm.setNInd(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.recogTheory, IndicatorT.ideaGeneration});

        double[] actual = CS2XB3.measures(Creativity);
        double[] expected = new double[]{0.126005256, 0.126985995, 0.522674669, 0.224334078};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // adding two LOsT with same name to same indicator
    @Test
    public void test_duplicatesAddLO()
    {
        CS2ME3.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles", 5, 21, 27, 7));
        assertEquals(1, CS2ME3.getLOs(IndicatorT.desPrinciples).length);
    }

    // adding two LOsT with same name to same indicator (temporary variable)
    @Test
    public void test_duplicatesAddLO2()
    {
        CourseT TempTester = new CourseT("Checks Duplicate Adds", CS2ME3_indicators);
        TempTester.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles", 4, 21, 2, 17));
        TempTester.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles", 5, 21, 27, 7));
        TempTester.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles Differed", 5, 21, 27, 7));
        TempTester.addLO(IndicatorT.desPrinciples, new LOsT("Software Engineering Principles Differed", 21, 2, 13, 7));
        assertEquals(2, TempTester.getLOs(IndicatorT.desPrinciples).length);
    }

    public static boolean arrayEqualityLOsT(LOsT[] a1, LOsT[] a2)
    {
        if (a1.length != a2.length)
            return false;
        Boolean[] results = new Boolean[a1.length];
        for (int i = 0; i < a1.length; i++)
            for (LOsT item : a2)
                if (a1[i].equals(item)) {
                    results[i] = true;
                    break;
                }
        for (boolean item : results)
            if (!item)
                return false;
        return true;
    }
}
