package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class SemiCircle extends Shape
{
    private final float radius;
    private final boolean isUp;
	public SemiCircle(float x0, float y0, float z0, float radius, ColorARGB color)
    {
        Point3F center=new Point3F(x0,y0,z0);
        super(center, color);
        this.radius = radius;
        this.isUp = true;
    }
    public SemiCircle(float x0, float y0, float z0, float radius, boolean isUp, ColorARGB color)
    {
        Point3F center=new Point3F(x0,y0,z0);
        super(center, color);
        this.radius = radius;
        this.isUp = isUp;
    }
	public SemiCircle(Point3F center, float radius, ColorARGB color)
    {
        super(center, color);
        this.radius = radius;
        this.isUp=true;
    }
    public SemiCircle(Point3F center, float radius, boolean isUp, ColorARGB color)
    {
        super(center, color);
        this.radius = radius;
        this.isUp = isUp;
    }
    public float radius()
    {
        return radius;
    }
    public boolean isUp()
    {
        return isUp;
    }
}
