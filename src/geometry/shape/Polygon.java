package geometry.shape;

import geometry.base.Point3F;
import geometry.base.Triangle;
import graphics.ColorARGB;
import linearalgebra.MatrixF;

public class Polygon extends Shape
{
    protected Point3F[] points;
    public Polygon(ColorARGB color, Point3F... points) {
        float x=0;
        float y=0;
        float z=0;
        for(int i=0; i<points.length; i++){
            x=x+points[i].x();
            y=y+points[i].y();
            z=z+points[i].z();
        }
        x=x/points.length;
        y=y/ points.length;
        z=z/ points.length;
        Point3F center=new Point3F(x,y,z);
        super(center, color);
        this.points = points;
    }
    public Polygon(Point3F center, ColorARGB color, Point3F... points) {
        super(center, color);
        this.points = points;
    }
    public Polygon(Polygon polygon){
        super(polygon.center(), polygon.color());
        this.points=new Point3F[polygon.count()];
        for (int i=0; i<polygon.count();i++){
            points[i]= polygon.point(i);
        }
    }
    public void rotate(float alpha, float beta, float gamma) {
        MatrixF m = MatrixF.rotationMatrix(alpha, beta, gamma);
        for (int i = 0; i < count(); i++) {
            points[i]=m.multiply(points[i]);
        }
    }
    public Polygon rotated(float alpha, float beta, float gamma) {
        Point3F[] points =new Point3F[count()];
        MatrixF m = MatrixF.rotationMatrix(alpha, beta, gamma);
        for (int i = 0; i < count(); i++) {
            points[i]=m.multiply(this.points[i]);
        }
        return new Polygon(center, color, points);
    }
    public void move(float x, float y, float z) {
        MatrixF m= MatrixF.translationMatrix(x,y,z);
        for(int i=0; i<count(); i++) {
            points[i] = m.multiply(points[i]);
        }
    }
    public Polygon moved(float x, float y, float z) {
        MatrixF m= MatrixF.translationMatrix(x,y,z);
        Point3F[] points=new Point3F[count()];
        Point3F center=m.multiply(this.center);
        for(int i=0; i<count(); i++) {
            points[i] = m.multiply(this.points[i]);
        }
        return new Polygon(center, color, points);
    }
    public Triangle[] toTriangles() {
        int count = this.count() - 2;
        Triangle[] triangles = new Triangle[count];
        Triangle triangle;
        for (int i = 0; i < count; i++) {
            triangle = new Triangle(points[0], points[i + 1], points[i + 2], color);
            triangles[i]=triangle;
        }
        return triangles;
    }
    public void transform(MatrixF m){
        for(int i=0; i<count(); i++) {
            points[i] = m.multiply(points[i]);
        }
    }
    public Polygon transformed(MatrixF m){
        Point3F[] points=new Point3F[count()];
        Point3F center=m.multiply(this.center);
        for(int i=0; i<count(); i++) {
            points[i] = m.multiply(this.points[i]);
        }
        return new Polygon(center, color, points);
    }
    public Point3F point(int i)
    {
        return points[i];
    }
    public int count()
    {
        return points.length;
    }
    public String toString() {
        String s = "[";
        for (int i = 0; i < count(); i++) {
            s = s + points[i].toString();
            if (i != count() - 1) {
                s = s + ", ";
            }
        }
        s = s + "]";
        return s;
    }
}
