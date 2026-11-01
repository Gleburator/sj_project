package mathematics;

import static mathematics.MathF.*;
public final class Complex extends Number {
    private final float x;
    private final float y;
    public Complex(String s) {
        Complex c = toComplex(s);
        this.x = c.re();
        this.y = c.im();
    }
    public Complex(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Complex(float x) {
        this.x = x;
        this.y = 0;
    }
    public Complex[] radical(int lambda) {
        float re;
        float im;
        Complex[] roots = new Complex[lambda];
        for (int i = 0; i < lambda; i++)
        {
            re = (MathF.pow(length(), 1.0f/ lambda) * cos((arg() + 2 * PI * i) / lambda));
            im = (MathF.pow(length(), 1.0f/ lambda) * sin((arg() + 2 * PI * i) / lambda));
            Complex c = new Complex(re, im);
            roots[i] = c;
        }
        return roots;
    }
    public Complex sqrt()
    {
        float re = (MathF.sqrt((length() + x) / 2));
        float im = (MathF.sqrt((length()-x)/ 2));
        return new Complex(re, im);
    }
    public float arg()
    {
        return atan2(y, x);
    }
    public float re()
    {
        return x;
    }
    public float im()
    {
        return y;
    }
    public Complex add(Complex c) {
        return new Complex(x+ c.re(), y + c.im());
    }
    public Complex add(float c) {
        return new Complex(x + c, y);
    }
    public Complex sub(Complex c) {
        return new Complex(x-c.re(), y -c.im());
    }
    public Complex sub(float c)
    {
        return new Complex(x - c, y);
    }
    public Complex multiply(Complex c1) {
        float a1 = x;
        float a2 = c1.re();
        float b1 = y;
        float b2 = c1.im();
        return new Complex(a1*a2-b1*b2, a1*b2 + a2*b1);
    }
    public Complex multiply(float c)
    {
        return new Complex(x*c, y*c);
    }
    public Complex division(Complex c)
    {
        float a1 = x;
        float a2 = c.re();
        float b1 = y;
        float b2 = c.im();
        return new Complex((a1*a2+b1*b2)/(a2*a2+b2*b2),-(a1*b2-a2*b1)/(a2 * a2 + b2 * b2));
    }
    public Complex division(float c)
    {
        return new Complex( x/c , y/c);
    }
    public Complex pow(int n) {
        if(n==0) {
            return new Complex(1, 0);
        }
        else {
            Complex c = new Complex(x, y);
            for (int i = 1; i < n; i++) {
                c= c.multiply(c);
            }
            return c;
        }
    }
    public float squaredLength() {
        return x*x+y*y;
    }
    public float length()
    {
        return MathF.sqrt(squaredLength());
    }
    public Complex conj()
    {
        return new Complex(x, -y);
    }
    public Complex invert(){
        return conj().division(squaredLength());
    }
    public String toString() {
        String s = "";
        String Re = Float.toString(normalize(x));
        String Im = Float.toString(normalize(y));
        if(x!=0) {
            if(y==1) {
                s = Re +"+"+"i";
            }
            else if(y==-1) {
                s = Re + "-" + "i";
            }
            else if(y==0) {
                s=Re;
            }
            else if(y>0) {
                s = Re + "+"+ Im + "i";
            }
            else {
                s = Re + Im + "i";
            }
        }
        else if(y!=0) {
            if (y == 1) {
                s = "i";
            }
            else if (y == -1) {
                s = "-" + "i";
            }
            else {
                s = Im + "i";
            }
        }
        else {
            s = "0";
        }
        return s;
    }
    public static Complex toComplex(String s) {
        String Re = "";
        String Im = "";
        float x;
        float y;
        boolean isRe = true;
        boolean isIm = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'i') {
                isRe = false;
                isIm = true;
                continue;
            }
            if (s.charAt(i) == '+') {
                isRe = true;
                isIm = false;
                continue;
            }
            if (s.charAt(i) == '-') {
                if (isIm) {
                    Im = s.charAt(i) + Im;
                }
                else {
                    Re = s.charAt(i) + Re;
                }
                isRe = true;
                isIm = false;
                continue;
            }
            if (isIm) {
                Im = s.charAt(i) + Im;
            }
            if (isRe) {
                Re = s.charAt(i) + Re;
            }
        }
        if (Re.isEmpty()) {
            x = 0;
        }
        else {
            x = Float.parseFloat(Re);
        }
        if (Im.isEmpty()) {
            y = 0;
        }
        else {
            y = Float.parseFloat(Im);
        }
        return new Complex(x, y);
    }

    @Override
    public int intValue() {
        return (int) x;
    }
    @Override
    public long longValue() {
        return (long) x;
    }
    @Override
    public float floatValue() {
        return x;
    }

    @Override
    public double doubleValue() {
        return  x;
    }
}

