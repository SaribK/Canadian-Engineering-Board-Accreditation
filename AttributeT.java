/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: AttributeT ADT class
 */

package src;

import java.util.*;

public class AttributeT
{
    private String name;
    private HashSet<IndicatorT> s;

    public AttributeT(String attribName, IndicatorT[] indicators)
    {
        name = attribName;
        s = new HashSet<>(Arrays.asList(indicators));
    }

    public String getName()
    {
        return name;
    }

    public IndicatorT[] getIndicators()
    {
        return s.toArray(new IndicatorT[0]);
    }
}
