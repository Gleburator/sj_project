package geometry.shape;

import geometry.base.Point3F;
import graphics.ColorARGB;
import linearalgebra.MatrixF;


import java.util.Arrays;

import static mathematics.MathF.*;
public class TriangleSphere extends Polyhedron {
    public TriangleSphere(float x0, float y0, float z0, float r, int stack, int sector, ColorARGB color){
        Point3F center=new Point3F(x0,y0, z0);
        Polygon[] polygons=createSphere(x0, y0, z0, r, stack, sector, color);
        super(center, polygons);
    }
    /*
    public static  <T> T[] joinArray(int len, T[]...arr){

    }

     */
    public static Polygon[] createSphere(float x0, float y0, float z0, float r, int stack, int sector, ColorARGB color){
        float secStep=2*PI/sector;
        float staStep=PI/stack;
        float secAngle, staAngle;
        float x1,y1,z1,xz1;
        float x2,y2,z2,xz2;
        float x3,z3,x4,z4;
        Polygon[] polygons=new Polygon[stack*sector];
        Point3F p1,p2,p3,p4;
        int index=0;
        staAngle=PI/2;
        for(int i=0; i<stack;i++){
            secAngle=0;
            xz1=r*cos(staAngle); xz2=r*cos(staAngle-staStep);
            y1=y0+r*sin(staAngle); y2=y0+r*sin(staAngle-staStep);
            for (int j=0;j<sector;j++){
                x1=x0+xz1*cos(secAngle+secStep); x2=x0+xz1*cos(secAngle);
                z1=z0+xz1*sin(secAngle+secStep); z2=z0+xz1*sin(secAngle);
                x3=x0+xz2*cos(secAngle); x4=x0+xz2*cos(secAngle+secStep);
                z3=z0+xz2*sin(secAngle); z4=z0+xz2*sin(secAngle+secStep);
                p1=new Point3F(x1,y1,z1);
                p2=new Point3F(x2,y1,z2);
                p3=new Point3F(x3,y2,z3);
                p4=new Point3F(x4,y2,z4);
                polygons[index]=new Polygon(color,p1,p2,p3,p4);
                secAngle=secAngle+secStep;
                index++;
            }
            staAngle=staAngle-staStep;
        }
        return polygons;
    }

    public static Polygon[] createSphereOctant(float r, int stack, int sector, ColorARGB color, Octant octant){
        Polygon[] polygons=new Polygon[stack*sector];
        final float dt=PI/(stack);
        final float dp=PI/(sector);
        float t=0;
        float p=0;
        float x1,y1,z1;
        float x2,y2,z2;
        float x3,y3,z3;
        float x4,y4,z4;
        Point3F p1,p2,p3,p4;
        int index=0;
        for (int i=0;i<stack;i++){
            for(int j=0;j<sector;j++){
                x1=r*sin(t)*cos(p); y1=r*sin(t)*sin(p); z1=r*cos(t);
                x2=r*sin(t)*cos(p+dp); y2=r*sin(t)*sin(p+dp); z2=r*cos(t);
                x3=r*sin(t+dt)*cos(p+dp); y3=r*sin(t+dt)*sin(p+dp); z3=r*cos(t+dt);
                x4=r*sin(t+dt)*cos(p); y4=r*sin(t+dt)*sin(p); z4=r*cos(t+dt);
                switch (octant){
                    case I: {
                        p3 =new Point3F(x1,y1,z1); p2 =new Point3F(x2,y2,z2);
                        p1 =new Point3F(x3,y3,z3); p4 =new Point3F(x4,y4,z4);
                        break;
                    }
                    case II: {
                        p1 =new Point3F(-x1,y1,z1); p2 =new Point3F(-x2,y2,z2);
                        p3 =new Point3F(-x3,y3,z3); p4 =new Point3F(-x4,y4,z4);
                        break;
                    }
                    case III: {
                        p3 =new Point3F(-x1,-y1,z1); p2 =new Point3F(-x2,-y2,z2);
                        p1 =new Point3F(-x3,-y3,z3); p4 =new Point3F(-x4,-y4,z4);
                        break;
                    }
                    case IV: {
                        p1 =new Point3F(x1,-y1,z1); p2 =new Point3F(x2,-y2,z2);
                        p3 =new Point3F(x3,-y3,z3); p4 =new Point3F(x4,-y4,z4);
                        break;
                    }
                    case V: {
                        p1 =new Point3F(x1,y1,-z1); p2 =new Point3F(x2,y2,-z2);
                        p3 =new Point3F(x3,y3,-z3); p4 =new Point3F(x4,y4,-z4);
                        break;
                    }
                    case VI: {
                        p3 =new Point3F(-x1,y1,-z1); p2 =new Point3F(-x2,y2,-z2);
                        p1 =new Point3F(-x3,y3,-z3); p4 =new Point3F(-x4,y4,-z4);
                        break;
                    }
                    case VII: {
                        p1=new Point3F(-x1,-y1,-z1); p2 =new Point3F(-x2,-y2,-z2);
                        p3 =new Point3F(-x3,-y3,-z3); p4 =new Point3F(-x4,-y4,-z4);
                        break;
                    }
                    case VIII: {
                        p3 =new Point3F(x1,-y1,-z1); p2 =new Point3F(x2,-y2,-z2);
                        p1 =new Point3F(x3,-y3,-z3); p4 =new Point3F(x4,-y4,-z4);
                        break;
                    }
                    default:{
                        p1 =null; p2 =null;
                        p3 =null; p4 =null;
                        break;
                    }
                }
                polygons[index]=new Polygon(color ,p1,p2,p3,p4);
                p=p+dp;
                index++;
            }
            t=t+dt;
            p=0;
        }
        return polygons;
    }

}


