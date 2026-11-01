package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;
import static java.lang.Math.*;
public class TriangleShape extends Polygon {
    public TriangleShape(Point3F p1, Point3F p2, Point3F p3, ColorARGB color){
        super(color,p1,p2,p3);
    }
    public TriangleShape(float x0, float y0, float z0, float a, float b, float angle, Plane plane, ColorARGB color){
        Point3F center=new Point3F(x0,y0,z0);
        super(center, color, createTriangle(x0,y0,z0,a,b,angle, plane));
    }
    public static Point3F[] createTriangle(float x0, float y0, float z0, float a, float b, float angle, Plane plane){
        Point3F[] points=new Point3F[3];
        float sinA=(float)sin(angle);
        float cosA=(float)cos(angle);
        float d=b/3;
        float shiftX=a*cosA/3;
        float shiftY=a*sinA/3;
        switch (plane){
            case XY:{
                points[0]=new Point3F(x0-2*d+shiftX, y0-shiftY, z0);
                points[1]=new Point3F(x0+2*d-shiftX, y0-shiftY, z0);
                points[2]=new Point3F(x0-d+2*shiftX, y0+2*shiftY, z0);
                break;
            }
            case YZ:{
                points[0]=new Point3F(x0, y0-2*d+shiftX, z0-shiftY);
                points[1]=new Point3F(x0, y0+2*d-shiftX, z0-shiftY);
                points[2]=new Point3F(x0, y0-d+2*shiftX, z0+2*shiftY);
                break;
            }
            case XZ:{
                points[0]=new Point3F(x0-2*d+shiftX, y0, z0-shiftY);
                points[1]=new Point3F(x0+2*d-shiftX, y0, z0-shiftY);
                points[2]=new Point3F(x0-d+2*shiftX, y0, z0+2*shiftY);
                break;
            }
        }
        return points;

    }
}
