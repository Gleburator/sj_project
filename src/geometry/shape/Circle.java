package geometry.shape;

import geometry.base.Point3F;
import geometry.base.Vector3F;
import graphics.ColorARGB;

public class Circle extends Shape
{
    private Vector3F radius;
	public Circle(float x0, float y0, float z0, float radius, Plane plane, ColorARGB color)
    {
        Point3F center=new Point3F(x0,y0,z0);
        super(center, color);
        switch (plane){
            case XY:{
                this.radius=new Vector3F(0,0,radius);
                break;
            }
            case YZ:{
                this.radius=new Vector3F(radius,0,0);
                break;
            }
            case XZ:{
                this.radius=new Vector3F(0,radius,0);
                break;
            }
        }
    }
	public Circle(Point3F center, float radius, Plane plane, ColorARGB color)
    {
        super(center, color);
        switch (plane){
            case XY:{
                this.radius=new Vector3F(0,0,radius);
                break;
            }
            case YZ:{
                this.radius=new Vector3F(radius,0,0);
                break;
            }
            case XZ:{
                this.radius=new Vector3F(0,radius,0);
                break;
            }
        }
    }
    public float radius()
    {
        return radius.length();
    }
}
