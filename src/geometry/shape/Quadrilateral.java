package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public abstract class Quadrilateral extends Polygon {
    protected Quadrilateral(Point3F center, ColorARGB color, Point3F...points){
        super(center,color, points);
    }
    public static Point3F[] createSquare(float x0, float y0, float z0, float a, Plane plane, Rotation rotation){
        Point3F[] points = new Point3F[4];
        float l=a/2;
        int i=0, j=0;
        switch (rotation){
            case CW:{
                i=1; j=3;
                break;
            }
            case CCW:{
                i=3; j=1;
                break;
            }
        }
        switch (plane) {
            case XY:{
                points[0] = new Point3F(x0 + l, y0 + l, z0);
                points[i] = new Point3F(x0 - l, y0 + l, z0);
                points[2] = new Point3F(x0 - l, y0 - l, z0);
                points[j] = new Point3F(x0 + l, y0 - l, z0);
                break;
            }
            case YZ:{
                points[0] = new Point3F(x0, y0 + l, z0 + l);
                points[i] = new Point3F(x0, y0 - l, z0 + l);
                points[2] = new Point3F(x0, y0 - l, z0 - l);
                points[j] = new Point3F(x0, y0 + l, z0 - l);
                break;
            }
            case XZ:{
                points[0] = new Point3F(x0 + l, y0, z0+l);
                points[i] = new Point3F(x0 + l, y0, z0-l);
                points[2] = new Point3F(x0 - l, y0, z0-l);
                points[j] = new Point3F(x0 - l, y0, z0+l);
                break;
            }
        }
        return points;
    }
    public static Point3F[] createRectangle(float x0, float y0, float z0, float a, float b, Plane plane, Rotation rotation){
        Point3F[] points = new Point3F[4];
        float l=a/2;
        float d=b/2;
        int i=0, j=0;
        switch (rotation){
            case CW:{
                i=1; j=3;
                break;
            }
            case CCW:{
                i=3; j=1;
                break;
            }
        }
        switch (plane){
            case XY:{
                points[0] = new Point3F(x0 + d, y0 + l, z0);
                points[i] = new Point3F(x0 - d, y0 + l, z0);
                points[2] = new Point3F(x0 - d, y0 - l, z0);
                points[j] = new Point3F(x0 + d, y0 - l, z0);
                break;
            }
            case YZ:{
                points[0] = new Point3F(x0, y0 + d, z0 + l);
                points[i] = new Point3F(x0, y0 - d, z0 + l);
                points[2] = new Point3F(x0, y0 - d, z0 - l);
                points[j] = new Point3F(x0, y0 + d, z0 - l);
                break;
            }
            case XZ:{
                points[0] = new Point3F(x0 + d, y0, z0+l);
                points[i] = new Point3F(x0 + d, y0, z0-l);
                points[2] = new Point3F(x0 - d, y0, z0-l);
                points[j] = new Point3F(x0 - d, y0, z0+l);
                break;
            }
        }
        return points;
    }
    public static Point3F[] createTrapezium(float x0, float y0, float z0, float a, float b, float h, float shift, Plane plane, Rotation rotation){
        Point3F[] points = new Point3F[4];
        float l=a/2;
        float d=b/2;
        float e=h/2;
        int i=0, j=0;
        switch (rotation){
            case CW:{
                i=1; j=3;
                break;
            }
            case CCW:{
                i=3; j=1;
                break;
            }
        }
        switch (plane){
            case XY:{
                points[0] = new Point3F(x0+  l+shift, y0 + e, z0);
                points[i] = new Point3F(x0 - l+shift, y0 + e, z0);
                points[2] = new Point3F(x0 - d, y0 - e, z0);
                points[j] = new Point3F(x0 + d, y0 - e, z0);
                break;
            }
            case YZ:{
                points[0] = new Point3F(x0, y0 + l+shift, z0 + e);
                points[i] = new Point3F(x0, y0 - l+shift, z0 + e);
                points[2] = new Point3F(x0, y0 - d, z0 - e);
                points[j] = new Point3F(x0, y0 + d, z0 - e);
                break;
            }
            case XZ:{
                points[0] = new Point3F(x0 + l+shift, y0, z0+e);
                points[i] = new Point3F(x0 + l+shift, y0, z0-e);
                points[2] = new Point3F(x0 - d, y0, z0-e);
                points[j] = new Point3F(x0 - d, y0, z0+e);
                break;
            }
        }
        return points;
    }
    public static Point3F[] createParallelogram(float x0, float y0, float z0, float a, float b, float angle, Plane plane, Rotation rotation){
        Point3F[] points = new Point3F[4];
        float sinA=(float)Math.sin(angle);
        float cosA=(float)Math.cos(angle);
        float l=a*sinA/2;
        float d=b/2;
        float shift=a*cosA/2;
        int i=0, j=0;
        switch (rotation){
            case CW:{
                i=1; j=3;
                break;
            }
            case CCW:{
                i=3; j=1;
                break;
            }
        }
        switch (plane){
            case XY:{
                points[0] = new Point3F(x0 + d+shift, y0 + l, z0);
                points[i] = new Point3F(x0 - d+shift, y0 + l, z0);
                points[2] = new Point3F(x0 - d-shift, y0 - l, z0);
                points[j] = new Point3F(x0 + d-shift, y0 - l, z0);
                break;
            }
            case YZ:{
                points[0] = new Point3F(x0, y0 + d+shift, z0 + l);
                points[i] = new Point3F(x0, y0 - d+shift, z0 + l);
                points[2] = new Point3F(x0, y0 - d-shift, z0 - l);
                points[j] = new Point3F(x0, y0 + d-shift, z0 - l);
                break;
            }
            case XZ:{
                points[0] = new Point3F(x0 + d+shift, y0, z0+l);
                points[i] = new Point3F(x0 + d+shift, y0, z0-l);
                points[2] = new Point3F(x0 - d-shift, y0, z0-l);
                points[j] = new Point3F(x0 - d-shift, y0, z0+l);
                break;
            }
        }
        return points;
    }

}
