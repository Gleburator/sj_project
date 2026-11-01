package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class Parallelogram extends Quadrilateral {
    public Parallelogram(float x0, float y0, float z0, float a, float b, float angle, Plane plane, Rotation rotation, ColorARGB color){
        Point3F center = new Point3F(x0, y0, z0);
        super(center, color, createParallelogram(x0, y0, z0, a, b, angle, plane, rotation));
    }
    public Parallelogram(Point3F center, float a, float b, float angle, Plane plane, Rotation rotation, ColorARGB color){
        float x0=center.x();
        float y0=center.y();
        float z0=center.z();
        super(center, color, createParallelogram(x0, y0, z0, a, b, angle, plane, rotation));
    }
}
