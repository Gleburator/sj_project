package mathematics;

import static java.lang.Math.abs;
import static java.lang.Math.round;

public final class MathF {
    private  MathF(){}
    public static float sin(float alpha){
        return (float) Math.sin(alpha);
    }
    public static float cos(float alpha){
        return (float) Math.cos(alpha);
    }
    public static float sqrt(float a){
        return (float) Math.sqrt(a);
    }
    public static float pow(float a,float n){
        return (float) Math.pow(a, n);
    }
    public static float atan2(float a, float b){
        return (float) Math.atan2(a,b);
    }
    public static float atan(float a){
        return (float) Math.atan(a);
    }
    public static float acos(float a){
        return (float) Math.acos(a);
    }
    public static float asin(float a){
        return (float) Math.asin(a);
    }
    public static float tan(float alpha){
        return (float) Math.tan(alpha);
    }
    public static float cot(float alpha){
        return (float)(1/Math.tan(alpha));
    }
    public static float abs(float a){
        return Math.abs(a);
    }
    public static int abs(int a){
        return Math.abs(a);
    }
    public static float sign(float a){
        return Math.signum(a);
    }
    public static float max(float a, float b){
        return Math.max(a,b);
    }
    public static float min(float a, float b){
        return Math.min(a,b);
    }
    public static int ipart(float x){
        return (int) Math.floor(x);
    }
    public static float fpart(float x){
        return x-ipart(x);
    }
    public static float normalize(float s) {
        float eps = 1e-10f;
        float rounded = s;
        if (abs(round(s) - s) < eps) {
            rounded = round(s);
            if(rounded==0) {
                rounded = 0;
            }
        }
        return rounded;
    }
    public static final float PI=(float)Math.PI;
}
