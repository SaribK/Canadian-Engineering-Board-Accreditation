/**
 * Author: Sarib Kashif
 * Revised: Mar 29, 2021
 *
 * Description: Tests for the ProgramT ADT class
 */

package src;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestProgramT
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

    private ProgramT P;

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
        P = new ProgramT();
        P.add(CS2ME3);
        P.add(CS2XB3);
    }

    @After
    public void tearDown()
    {
        P = null;
        CS2ME3 = null;
        CS2XB3 = null;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_exceptionMeasures()
    {
        P.measures();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_exceptionMeasuresIndicator()
    {
        P.measures(IndicatorT.math);
    }

    @Test
    public void test_exceptionMeasuresAttribute()
    {
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.math});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.084282460, 0.1298405466, 0.36674259681, 0.419134396};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    @Test
    public void test_exceptionMeasuresAttribute1()
    {
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.057471264, 0.137931034, 0.540229885, 0.264367816};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make NLOs true
    @Test
    public void test_exceptionMeasuresAttribute2()
    {
        Norm.setNLOs(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.math});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.09628902233, 0.13231216401, 0.39002720253, 0.38137161};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make NInd true
    @Test
    public void test_exceptionMeasuresAttribute3()
    {
        Norm.setNInd(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.recogTheory, IndicatorT.math});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.09871691409, 0.12063049217, 0.4294518788, 0.351200716};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make NAtt true
    @Test
    public void test_exceptionMeasuresAttribute4()
    {
        Norm.setNAtt(true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.recogTheory, IndicatorT.math});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.12678571429, 0.1459218147, 0.453788610, 0.27350386};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }

    // make all Norms true
    @Test
    public void test_exceptionMeasuresAttribute5()
    {
        Norm.setNorms(true, true, true);
        AttributeT Creativity = new AttributeT("Creativity", new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.recogTheory, IndicatorT.math});
        double[] actual = P.measures(Creativity);
        double[] expected = new double[]{0.142175354, 0.15106325945, 0.4361799484, 0.270581438};
        assertTrue(TestLOsT.doubleArrayEquality(actual, expected));
    }
}
