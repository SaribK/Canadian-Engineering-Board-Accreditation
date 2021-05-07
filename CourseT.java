/**
 * @file CourseT.java
 * @author Sarib Kashif (kashis2)
 * @brief CourseT ADT class that inherits the Measures interface
 * @date Mar 29, 2021
 */

package src;

import java.util.*;

/**
 * @brief An ADT that represents a course
 * @details The course is represented in the order (course name, indicators).
 */
public class CourseT implements Measures{

    private String name;
    private HashMap<IndicatorT, HashSet<LOsT>> m;

    /**
     * @brief Initializes a course object.
     * @param courseName A string for the name of the course
     * @param indicators An array of the indicators for the course
     */
    public CourseT(String courseName, IndicatorT[] indicators)
    {
        name = courseName;
        m = new HashMap<>();
        for (IndicatorT indicator : indicators) {
            HashSet<LOsT> set = new HashSet<>();
            m.put(indicator, set);
        }
    }

    /**
     * @brief Gets the course name
     * @return The name of the course
     */
    public String getName() { return name; }

    /**
     * @brief Gets the indicators of the course
     * @return The array of indicators for the course
     */
    public IndicatorT[] getIndicators()
    {
        return m.keySet().toArray(new IndicatorT[0]);
    }

    /**
     * @brief Gets the learning outcomes of the course associated
     * with the given indicator
     * @param indicator An IndicatorT object for the course
     * @return The array of learning outcomes of the course
     * for the provided indicator
     */
    public LOsT[] getLOs(IndicatorT indicator)
    {
        HashSet<LOsT> temp = m.get(indicator);
        LOsT[] arr = {};
        if (temp == null)
            return arr;
        LOsT[] ans = new LOsT[temp.size()];
        return temp.toArray(ans);
    }

    /**
     * @brief Adds a learning outcome to the set of LO's stored
     * with the provided indicator
     * @details If the indicator is not one of the keys, then
     * the provided learning outcome is not added. If there is already
     * a learning outcome with the same name (equals), LO is not added.
     * @param indicator An IndicatorT object for the course
     * @param outcome The learning outcome that is being added
     */
    public void addLO(IndicatorT indicator, LOsT outcome)
    {
        if (m.containsKey(indicator))
        {
            for (LOsT item : m.get(indicator))
                if (item.equals(outcome))
                    return;
            m.get(indicator).add(outcome);
        }
    }

    /**
     * @brief Deletes a learning outcome from the array of LO's stored
     * with the provided indicator
     * @details If the indicator is not one of the keys, then
     * the provided learning outcome is not deleted.
     * @param indicator An IndicatorT object for the course
     * @param outcome The learning outcome that is being deleted
     */
    public void delLO(IndicatorT indicator, LOsT outcome)
    {
        if (m.containsKey(indicator))
            m.get(indicator).removeIf(LO -> LO.equals(outcome));
    }

    /**
     * @brief Searches for the array of learning outcomes using
     * the provided indicator
     * @param indicator An IndicatorT object for the course
     * @param outcomes The array of learning outcomes that are being
     *                 searched for
     * @return true if the learning outcomes mapped to the provided
     * indicator are equal, and false otherwise
     */
    public boolean member(IndicatorT indicator, LOsT[] outcomes)
    {
        if (m.containsKey(indicator))
        {
            LOsT[] x = m.get(indicator).toArray(new LOsT[0]);
            if (outcomes.length != x.length)
                return false;
            Boolean[] is_equal = new Boolean[outcomes.length];
            int i = 0;
            for (LOsT lOsT1 : outcomes)
            {
                for (LOsT lOsT2 : x)
                {
                    if (lOsT1.equals(lOsT2))
                    {
                        is_equal[i] = true;
                        break;
                    }
                }
                i++;
            }
            for (boolean item : is_equal)
                if (!item)
                    return false;
            return true;
        }
        return false;
    }

    /**
     * @brief Override's the measure() method defined in the Measures interface
     * @throws UnsupportedOperationException because the method is not supported in CourseT
     */
    @Override
    public double[] measures() {
        throw new UnsupportedOperationException("This version of measures() is not applicable to CourseT");
    }

    /**
     * @brief Override's the measures(IndicatorT) function
     * @details Measures the results of the learning outcome
     * in terms of the number of students that were below, marginal,
     * meeting, and exceeding expectations, using the provided indicator.
     * @param ind IndicatorT object used as key to access array of LOsT
     * @return Array of 4 doubles showing the measures of ind. Exact
     * output is determined by the boolean values of the Norms.
     */
    @Override
    public double[] measures(IndicatorT ind) {
        double[] temp = new double[] {0.0, 0.0, 0.0, 0.0};
        if (this.getLOs(ind).length == 0)
            return temp;
        LOsT[] temp2 = this.getLOs(ind);
        for (LOsT lOsT : temp2)
            temp = sumMeas(temp, lOsT.measures());
        if (Norm.getNInd())
            return Services.normal(temp);
        return temp;
    }

    /**
     * @brief Override's the measures(AttributeT) function
     * @details Measures the results of the learning outcome
     * in terms of the number of students that were below, marginal,
     * meeting, and exceeding expectations, using the provided attribute.
     * @param att AttributeT object used used for it's array of indictors,
     *            on which measures is individually called.
     * @return Array of 4 doubles showing the measures of att. Exact
     * output is determined by the boolean values of the Norms.
     */
    @Override
    public double[] measures(AttributeT att) {
        double[] temp = new double[] {0.0, 0.0, 0.0, 0.0};
        if (att.getIndicators().length == 0)
            return temp;
        IndicatorT[] temp2 = att.getIndicators();
        for (IndicatorT indicator : temp2)
            temp = sumMeas(temp, this.measures(indicator));
        if (Norm.getNAtt())
            return Services.normal(temp);
        return temp;
    }

    private double[] sumMeas(double[] a, double[] b)
    {
        return new double[]{a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
    }
}
