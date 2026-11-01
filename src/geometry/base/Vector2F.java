package geometry.base;

import linearalgebra.MatrixF;
import static mathematics.MathF.*;

public final class Vector2F extends Vector {
    private final float x;
    private final float y;
    public Vector2F(float[] v){
        this.x=v[0];
        this.y=v[1];
    }
    public Vector2F()
    {
        this.x = 0;
        this.y = 0;
    }
    public Vector2F(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2F(Point2F p1, Point2F p2)
    {
        this.x = p2.x() - p1.x();
        this.y = p2.y() - p1.y();
    }
    public float x()
    {
        return x;
    }
    public float y()
    {
        return y;
    }
    public MatrixF toMatrix() {
        MatrixF m = new MatrixF(2, 1);
        m.set(0, 0, x);
        m.set(1, 0, y);
        return m;
    }
    public Vector2F normalize(){
        return new Vector2F(x/length(),y/length());
    }
    public Vector2F add(Vector2F v)
    {
        float x2, y2;
        x2 = x + v.x();
        y2 = y + v.y();
        return new Vector2F(x2, y2);
    }
    public Vector2F sub(Vector2F v)
    {
        float x2, y2;
        x2 = x - v.x();
        y2 = y - v.y();
        return new Vector2F(x2, y2);
    }
    public float dot(Vector2F v)
    {
        return x*v.x()+ y*v.y();
    }
    public Vector2F multiply(float lambda)
    {
        float x2, y2, z2;
        x2 = x *lambda;
        y2 = y *lambda;
        return new Vector2F(x2, y2);
    }
    public Vector2F division(float lambda)
    {
        float x2, y2;
        x2 = x /lambda;
        y2 = y /lambda;
        return new Vector2F(x2, y2);
    }
    public Vector3F cross(Vector2F v)
    {
        float x2, y2, z2;
        x2 = 0;
        y2 = 0;
        z2 = (x * v.y() - v.x() * y);
        return new Vector3F(x2, y2, z2);
    }
    public Vector2F negative(){
        return new Vector2F(-x,-y);
    }
    public boolean equals(Vector2F v)
    {
        if (v.x() == x && v.y() == y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public float length()
    {
        return sqrt(squaredLength());
    }
    public float squaredLength()
    {
        return x * x + y * y;
    }

    public float vectorAngle(Vector2F v)
    {
        float angle, scalar, length;
        length=this.length();
        if ((length * v.length()) != 0)
        {
            scalar =this.dot(v);
            angle = acos(scalar / (length * v.length()));
            if(angle>PI){
                angle=PI-angle;
            }
        }
        else
        {
            angle = 0;
        }
        return angle;
    }
    public String toString()
    {
        String s = "[" + x + " ," + y  + "]";
        return s;
    }
    public Point2F toPoint2F(){
        return new Point2F(x,y);
    }
    public static Vector2F X=new Vector2F(1,0);
    public static Vector2F Y=new Vector2F(0,1);
}
