package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;

public class Cube extends Polyhedron {
    public Cube(float x0, float y0, float z0, float a, ColorARGB color){
        Polygon[] polygons=new Polygon[6];
        Point3F center=new Point3F(x0,y0,z0);
        float l=a/2;
        polygons[0]= new Square(x0,y0, z0+l, a, Plane.XY, Rotation.CW, color);
        polygons[1]=new Square(x0,y0, z0-l, a, Plane.XY, Rotation.CCW, color);
        polygons[2]=new Square(x0+l,y0, z0, a, Plane.YZ, Rotation.CW, color);
        polygons[3]=new Square(x0-l,y0, z0, a, Plane.YZ, Rotation.CCW, color);
        polygons[4]=new Square(x0,y0+l, z0, a, Plane.XZ,Rotation.CW, color);
        polygons[5]=new Square(x0,y0-l, z0, a, Plane.XZ,Rotation.CCW, color);
        super(center, polygons);
    }

}

