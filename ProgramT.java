/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: ProgramT ADT class that inherits the Measures interface and inherits the HashSet object
 */

package src;

import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures {

    @Override
    public double[] measures() {
        throw new UnsupportedOperationException("This version of measures() is not applicable to ProgramT");
    }

    @Override
    public double[] measures(IndicatorT ind) {
        throw new UnsupportedOperationException("This version of measures() is not applicable to ProgramT");
    }

    @Override
    public double[] measures(AttributeT att) {
        double[] temp = new double[] {0.0, 0.0, 0.0, 0.0};
        for (CourseT course : this)
            temp = sumMeas(temp, course.measures(att));
        return Services.normal(temp);
    }

    private double[] sumMeas(double[] a, double[] b)
    {
        return new double[]{a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
    }
}
