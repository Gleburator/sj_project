package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class Square extends Quadrilateral {
    public Square(float x0, float y0, float z0, float a, Plane plane, Rotation rotation, ColorARGB color) {
        Point3F center = new Point3F(x0, y0, z0);
        super(center, color, createSquare(x0, y0, z0, a, plane, rotation));
    }
    public Square(Point3F center, float a, Plane plane, Rotation rotation, ColorARGB color) {
        float x0=center.x();
        float y0=center.y();
        float z0=center.z();
        super(center, color, createSquare(x0, y0, z0, a, plane, rotation));
    }
}
