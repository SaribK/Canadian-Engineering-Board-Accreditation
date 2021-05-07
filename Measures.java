/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Interface for Measures
 */

package src;

interface Measures
{
    double[] measures() throws UnsupportedOperationException;
    double[] measures(IndicatorT ind) throws UnsupportedOperationException;
    double[] measures(AttributeT att) throws UnsupportedOperationException;
}
