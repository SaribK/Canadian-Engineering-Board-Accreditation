/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Static function normal()
 */

package src;

public class Services {
    public static double[] normal(double[] v)
    {
        double[] ans = new double[v.length];
        double sum = 0;
        for (double aDouble : v) sum += aDouble;
        for (int i = 0; i < v.length;i++) ans[i] = v[i] / sum;
        return ans;
    }
}
