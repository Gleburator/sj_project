package mathematics;

public final class Rational {
    private final int num;
    private final int deno;
    public Rational(int num, int deno)
    {
        if(deno==0)
        {
            throw new ArithmeticException("Знаменатель не должен быть равен нулю");
        }
        this.num = num;
        this.deno = deno;
    }
    public Rational(int num)
    {
        this.num = num;
        this.deno = 1;
    }
    public int num()
    {
        return num;
    }
    public int deno()
    {
        return deno;
    }
    public String toString()
    {
        String Num = Integer.toString(num);
        String Deno = Integer.toString(deno);
        String s = "";
        int length1 = Num.length();
        int length2 = Deno.length();
        int length;
        String indent1 = "";
        String indent2 = "";
        if (length1 >= length2)
        {
            length = length1;
            for (int i = 0; i < (length1 - length2) / 2; i++)
            {
                indent2 = indent2 + " ";
            }
        }
        else
        {
            length = length2;
            for (int i = 0; i < (length2 - length1) / 2; i++)
            {
                indent1 = indent1 + " ";
            }
        }
        s = s + indent1 + Num + indent1;
        if (deno != 1)
        {
            s = s + "\n";
            for (int i = 0; i < length; i++)
            {
                s = s + "―";
            }
            s = s + "\n";
            s = s + indent2 + Deno + indent2;
        }
        return s;
    }
    public Rational add(Rational f1)
    {
        int num = this.num * f1.deno() + f1.num() * this.deno;
        int deno = this.deno * f1.deno();
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational add(int f1) {
        int num = this.num+ f1 * this.deno;
        int deno = this.deno;
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational sub(Rational f1) {
        int num = this.num * f1.deno() - f1.num() * this.deno;
        int deno = this.deno * f1.deno();
        Rational f2 = new Rational(num, deno);
        return f2.simplified();
    }
    public Rational sub(int f1) {
        int num = this.num- f1 * this.deno;
        int deno = this.deno;
        Rational f2 = new Rational(num, deno);
        return f2.simplified();
    }

    public Rational multiply(Rational f1) {
        int num = this.num * f1.num();
        int deno = this.deno * f1.deno();
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational multiply(int f1) {
        int num = this.num * f1;
        int deno = this.deno;
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational division(Rational f1) {
        int num = this.num * f1.deno();
        int deno = this.deno * f1.num();
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational division(int f1) {
        int num = this.num;
        int deno = this.deno*f1;
        Rational f2 = new Rational(num, deno);
        return f2.simplified();

    }
    public Rational pow(int n) {
        if (n == 0) {
            return new Rational(1, 1);
        }
        else {
            Rational f = new Rational(num, deno);
            for(int i=1; i<n; i++)
            {
                f = f.multiply(f);
            }
            return f.simplified();
        }
    }
    public Rational simplified() {
        int gcd = Formulas.gcd(this.num, this.deno);
        int num = this.num / gcd;
        int deno = this.deno / gcd;
        return new Rational(num, deno);
    }
}

