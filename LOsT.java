/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: LOsT ADT class that inherits the Measures interface
 */

package src;

public class LOsT implements Measures {

    private String name;
    private int n_blw;
    private int n_mrg;
    private int n_mts;
    private int n_exc;

    public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc)
    {
        if (nblw < 0 || nmrg < 0 || nmts < 0 || nexc < 0)
            throw new IllegalArgumentException("Negative numbers in argument");
        if (nblw == 0 && nmrg == 0 && nmts == 0 && nexc == 0)
            throw new IllegalArgumentException("All numbers in argument are 0");
        name = topic;
        n_blw = nblw;
        n_mrg = nmrg;
        n_mts = nmts;
        n_exc = nexc;
    }

    public String getName() { return name; }

    @Override
    public boolean equals(Object o)
    {
        LOsT temp = (LOsT) o;
        return temp.getName().equals(name);
    }

    @Override
    public double[] measures() {
        double[] temp = new double[]{n_blw, n_mrg, n_mts, n_exc};
        if (!Norm.getNLOs())
            return temp;
        return Services.normal(temp);
    }

    @Override
    public double[] measures(IndicatorT ind) {
        throw new UnsupportedOperationException("This version of measures() is not applicable to LOsT");
    }

    @Override
    public double[] measures(AttributeT att) {
        throw new UnsupportedOperationException("This version of measures() is not applicable to LOsT");
    }
}
