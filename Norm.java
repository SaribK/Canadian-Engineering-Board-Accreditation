/**
 * Author: Sarib Kashif (kashis2)
 * Revised: Mar 29, 2021
 *
 * Description: Static Norm class (Abstract Object)
 */

package src;

public class Norm {

    private static boolean normLOs;
    private static boolean normInd;
    private static boolean normAtt;

    public static void setNorms(boolean nLOs, boolean nInd, boolean nAtt)
    {
        normLOs = nLOs;
        normInd = nInd;
        normAtt = nAtt;
    }

    public static boolean getNLOs() { return normLOs; };

    public static boolean getNInd() { return normInd; };

    public static boolean getNAtt() { return normAtt; };

    public static void setNLOs(boolean nLOs) { normLOs = nLOs; };

    public static void setNInd(boolean nInd) { normInd = nInd; };

    public static void setNAtt(boolean nAtt) { normAtt = nAtt; };

}
