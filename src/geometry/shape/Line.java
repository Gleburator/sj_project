package geometry.shape;

import geometry.base.Point3F;

public class Line
{
    Point3F p1;
    Point3F p2;
    public Line(Point3F p1, Point3F p2)
    {
        this.p1 = p1;
        this.p2 = p2;
    }
    public Line(float x1, float y1, float z1, float x2, float y2, float z2)
    {
        this.p1 = new Point3F(x1, y1, z1);
        this.p2 = new Point3F(x2, y2, z2);
    }
    public Point3F point1()
    {
        return p1;
    }
    public Point3F point2()
    {
        return p2;
    }
}
