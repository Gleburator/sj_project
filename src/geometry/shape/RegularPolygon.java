package geometry.shape;
import geometry.base.Point3F;
import graphics.ColorARGB;

import static mathematics.MathF.*;
public class RegularPolygon extends Polygon {
    public RegularPolygon(int n, float x0, float y0, float z0, float R, Plane plane, ColorARGB color){
        Point3F center=new Point3F(x0, y0, z0);
        Point3F[] points=new Point3F[n];
        float x, y, z;
        switch (plane){
            case XY:{
                for(int i=0;i<n; i++){
                    x=x0+R*cos(2*PI*i/n);
                    y=y0+R*sin(2*PI*i/n);
                    points[i]=new Point3F(x,y, 0);
                }
                break;
            }
            case YZ:{
                for(int i=0;i<n; i++){
                    y=x0+R*cos(2*PI*i/n);
                    z=z0+R*sin(2*PI*i/n);
                    points[i]=new Point3F(0,y, z);
                }
                break;
            }
            case XZ:{
                for(int i=0;i<n; i++){
                    x=x0+R*cos(2*PI*i/n);
                    z=z0+R*sin(2*PI*i/n);
                    points[i]=new Point3F(x,0, z);
                }
                break;
            }
        }
        super(center, color, points);
    }
}
