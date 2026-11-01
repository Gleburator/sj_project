package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class Rectangle extends Quadrilateral {
    public Rectangle(float x0, float y0, float z0, float a, float b, Plane plane,Rotation rotation, ColorARGB color) {
        Point3F center = new Point3F(x0, y0, z0);
        super(center, color, createRectangle(x0,y0,z0,a,b, plane, rotation));
    }
}
