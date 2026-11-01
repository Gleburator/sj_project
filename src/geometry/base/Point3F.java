package geometry.base;


import static mathematics.MathF.*;
import linearalgebra.Point4F;
import linearalgebra.QuaternionF;

import java.util.Comparator;

public final class Point3F extends Point{
    private final float x;
    private final float y;
    private final float z;
    public Point3F(float[] p){
        this.x=p[0];
        this.y=p[1];
        this.z=p[2];
    }
    public Point3F(Point3F p){
        this.x=p.x();
        this.y=p.y();
        this.z=p.z();
    }
    public Point3F(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3F add(Vector3F v){
        return new Point3F(x+v.x(), y+v.y(), z+ v.z());
    }
    public Point3F sub(Vector3F v){
        return new Point3F(x-v.x(), y-v.y(), z-v.z());
    }
    public float x()
    {
        return x;
    }
    public float y()
    {
        return y;
    }
    public float z()
    {
        return z;
    }
    public float squaredDist(Point3F p)
    {
        float x, y, z;
        x = this.x - p.x();
        y = this.y - p.y();
        z = this.z - p.z();
        return x * x + y * y + z * z;
    }
    public float dist(Point3F p) {
        return sqrt(squaredDist(p));
    }
    public String toString()
    {
        String s = "{" + x + ", " + y + ", " + z + "}"; ;
        return s;
    }
    public Vector3F toVector3F(){
        return new Vector3F(x,y,z);
    }
    public Point4F toPoint4F(){
        return new Point4F(x,y,z,1);
    }

    public Point3F rotated(QuaternionF q){
        Point3F p=new Point3F(x,y,z);
        QuaternionF q1= q.multiply(p).multiply(q.invert());
        return new Point3F(q1.x(),q1.y(),q1.z());
    }

    public Point2F projected(){
        return new Point2F(x,y);
    }
    public static final Comparator<Point3F> COMPARE_Y=new Comparator<Point3F>() {
        @Override
        public int compare(Point3F p1, Point3F p2) {
            return Float.compare(p1.y(), p2.y());
        }
    };
    public static final Comparator<Point3F> COMPARE_X=new Comparator<Point3F>() {
        @Override
        public int compare(Point3F p1, Point3F p2) {
            return Float.compare(p1.x(), p2.x());
        }
    };
    public static final Comparator<Point3F> COMPARE_Z=new Comparator<Point3F>() {
        @Override
        public int compare(Point3F p1, Point3F p2) {
            return Float.compare(p1.z(), p2.z());
        }
    };
}
