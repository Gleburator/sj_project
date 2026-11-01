package mathematics;
import static mathematics.MathF.*;
public class Formulas
{
    private Formulas(){}
    public static float lawOfCosines(float a, float b, float c, Angle angle){
        float result=0;
        switch (angle){
            case ALPHA:{
                result=acos((b*b+c*c-a*a)/(2*b*c));
                break;
            }
            case BETA:{
                result=acos((a*a+c*c-b*b)/(2*a*c));
                break;
            }
            case GAMMA:{
                result=acos((a*a+b*b-c*c)/(2*a*b));
                break;
            }
        }
        return result;

    }
    public static int gcd(int a, int b)
    {
        while (b != 0)
        {
            b = a % (a = b);
        }
        return abs(a);
    }
    public static float gcd(float a, float b)
    {
        while (b != 0)
        {
            b = a % (a = b);
        }
        return abs(a);
    }
    public static int kronekier(int i, int j) {
        if (i == j) {
            return 1;
        }
        else {
            return 0;
        }
    }
    public static int leviCivita(int i, int j, int k) {
        if ((i == 1 && j == 2 && k == 3) ||
            (i == 2 && j == 3 && k == 1) ||
            (i == 3 && j == 1 && k == 2)) {
            return 1;
        }
        else if ((i == 3 && j == 2 && k == 1) ||
                 (i == 1 && j == 3 && k == 2) ||
                 (i == 2 && j == 1 && k == 3)) {
            return -1;
        }
        else {
            return 0;
        }
    }
    public static int leviCivita(int i, int j) {
        if (i == 1 && j == 2) {
            return 1;
        }
        else if (i == 2 && j == 1) {
            return -1;
        }
        else {
            return 0;
        }
    }
    public static int factorial(int n) {
        int i = 1;
        for (int k = 1; k <= n; k++) {
            i = i * k;
        }
        return i;
    }
    public final static float PHI=1.61803398874989484820458683436563811772030917980576f;
}
