package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public abstract class Figure {
    protected Point3F center;
    public Figure(Point3F center){
        this.center=center;
    }
    public Figure(float x0, float y0, float z0){
        this.center=new Point3F(x0,y0,z0);
    }
    public Point3F center(){
        return center;
    }
}
