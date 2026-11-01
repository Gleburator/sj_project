package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public abstract class Shape {
    protected final Point3F center;
    protected final ColorARGB color;
    public Shape(Point3F center, ColorARGB color){
        this.center=center;
        this.color=color;
    }
    public Shape(float x0, float y0, float z0, ColorARGB color){
        this.center=new Point3F(x0,y0,z0);
        this.color=color;
    }
    public Point3F center() {
        return center;
    }
    public ColorARGB color(){
        return color;
    }
}
