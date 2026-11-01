package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class Trapezium extends Quadrilateral {
    public Trapezium(float x0, float y0, float z0, float a, float b, float h, float shift, Plane plane, Rotation rotation, ColorARGB color){
        Point3F center=new Point3F(x0,y0,z0);
        super(center, color, createTrapezium(x0, y0, z0, a, b, h, shift,plane,rotation));
    }
    public Trapezium(Point3F center, float a, float b, float h, float shift, Plane plane, Rotation rotation, ColorARGB color){
        float x0=center.x();
        float y0=center.y();
        float z0=center.z();
        super(center, color, createTrapezium(x0, y0, z0, a, b, h, shift, plane, rotation));
    }

}
