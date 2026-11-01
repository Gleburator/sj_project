package geometry.base;

import graphics.ColorARGB;
import linearalgebra.MatrixF;

import java.util.Comparator;

public final class Triangle {
    private final Point3F p1;
    private final Point3F p2;
    private final Point3F p3;
    private final Vector3F normal;
    private final ColorARGB color;
    public Triangle(Point3F p1, Point3F p2, Point3F p3, ColorARGB color)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.color=color;
        Vector3F p1p2=new Vector3F(p1,p2);
        Vector3F p1p3=new Vector3F(p1,p3);
        this.normal=p1p2.cross(p1p3).normalize();

    }
    public Triangle(Point3F p1, Point3F p2, Point3F p3, Vector3F normal,ColorARGB color)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.normal=normal;
        this.color=color;
    }
    public Point3F point1()
    {
        return p1;
    }
    public Point3F point2()
    {
        return p2;
    }
    public Point3F point3()
    {
        return p3;
    }
    public Vector3F normal(){
        return normal;
    }
    public String toString()
    {
        String s = "["+p1.toString()+", " + p2.toString() + ", " + p3.toString() + "]";
        return s;
    }
    public ColorARGB color(){
        return color;
    }
    public Triangle rotated(float alpha, float beta, float gamma){
        MatrixF m= MatrixF.rotationMatrix(alpha, beta, gamma);
        Point3F p1=m.multiply(this.p1);
        Point3F p2=m.multiply(this.p2);
        Point3F p3=m.multiply(this.p3);
        Vector3F normal=m.multiply(this.normal);
        return new Triangle(p1,p2,p3,normal,color);
    }
    public Triangle moved(float x, float y, float z){
        MatrixF m= MatrixF.translationMatrix(x, y, z);
        Point3F p1=m.multiply(this.p1);
        Point3F p2=m.multiply(this.p2);
        Point3F p3=m.multiply(this.p3);
        Vector3F normal=m.multiply(this.normal);
        return new Triangle(p1,p2,p3, normal, color);
    }
    public Triangle transformed(MatrixF m){
        Point3F p1=m.multiply(this.p1);
        Point3F p2=m.multiply(this.p2);
        Point3F p3=m.multiply(this.p3);
        Vector3F normal=m.multiply(this.normal);
        return new Triangle(p1,p2,p3,normal,color);
    }
    public float area(){
        float area=((p2.x()-p1.x())*(p3.y()-p1.y())-(p3.x()-p1.x())*(p2.y()-p1.y()))/2;
        return area;
    }
    public Point3F center(){
        float x=(p1.x()+p2.x()+p3.x())/3;
        float y=(p1.y()+p2.y()+p3.y())/3;
        float z=(p1.z()+p2.z()+p3.z())/3;
        return new Point3F(x,y,z);
    }
    public static final Comparator<Triangle> COMPARE_Z=new Comparator<Triangle>() {
        @Override
        public int compare(Triangle t1, Triangle t2) {
            return Float.compare(t1.center().z(), t2.center().z());
        }
    };
}
