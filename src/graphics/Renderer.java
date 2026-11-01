package graphics;


import geometry.base.*;
import linearalgebra.MatrixF;

import linearalgebra.Point4F;

import world.Space;


import java.util.Arrays;

import static mathematics.MathF.*;


public class Renderer{

    PixelBuffer buffer;
    ZBuffer zBuffer;

    MatrixF projection;
    MatrixF viewPort;
    MatrixF camera;
    public Renderer(int width, int height){
        this.buffer=new PixelBuffer(width, height);
        this.zBuffer=new ZBuffer(width,height);


        MatrixF m1= MatrixF.invertYMatrix();

        MatrixF m2= MatrixF.perspectiveMatrix(PI/2, (float) width/height, -500, -3000);


        this.projection= m2.multiply(m1);
        this.viewPort=MatrixF.viewPort(width,height, 0, 1);
        this.camera=MatrixF.lookAtMatrix(new Point3F(0,0,0), new Point3F(0,0,-1));

    }
    public void render(Space space){
        this.camera = MatrixF.lookAtMatrix(space.camera());
        Object3 obj;
        Triangle tri;
        for(int i=0;i<space.count();i++){
            obj=space.get(i).body();
            for(int j=0; j<obj.count();j++){
                tri=obj.get(j);
                Vector3F v=new Vector3F(space.camera().center(), tri.center());
                if(v.dot(tri.normal())<0){
                    render(tri);
                }
            }
        }
    }
    public void render(Triangle triangle){
        MatrixF m=projection.multiply(camera);

        Point4F p1=m.multiply(triangle.point1().toPoint4F());
        Point4F p2=m.multiply(triangle.point2().toPoint4F());
        Point4F p3=m.multiply(triangle.point3().toPoint4F());

        if(p1.w()<=0||p2.w()<=0||p3.w()<=0){
            return;
        }
        triangle=triangle.transformed(m);
        ColorARGB color=triangle.color();
        Point3F a=p1.toPoint3F();
        Point3F b=p2.toPoint3F();
        Point3F c=p3.toPoint3F();
        Point3F[] p=cohen_sutherland(a,b,c);

        for(int i=0;i<p.length;i++){
            p[i]=viewPort.multiply(p[i]);
        }

        fillConvex(p, color);
    }
    public Point3F[] cohen_sutherland(Point3F a,Point3F b, Point3F c) {
        Point3F[] p1=new Point3F[]{a, b, c, null, null, null, null};
        Point3F[] p2=new Point3F[]{a, b, c, null, null, null, null};
        Point3F[] p =new Point3F[]{};
        int len=3;
        len=clipLeft(len, p1,p2);
        if(len>0){
            len=clipRight(len, p2, p1);
            if(len>0){
                len=clipUp(len, p1, p2);
                if(len>0){
                    len=clipDown(len, p2, p1);
                    if(len>0){
                        len=clipForward(len, p1, p2);
                        if(len>0){
                            len=clipBack(len, p2, p1);
                            if(len>0){
                                p=Arrays.copyOf(p1, len);
                            }
                        }
                    }
                }
            }
        }
        return p;
    }
    public void fillConvex(Point3F[] p, ColorARGB color){
        if(p.length>=3){
            Point3F[][] t=toTriangles(p);
            for(int i=0;i<t.length;i++){
                fillTriangle(t[i][0],t[i][1],t[i][2], color);
            }
        }
    }
    public void fillTriangle(Point3F p1, Point3F p2, Point3F p3, ColorARGB color){
        Vector3F v12=new Vector3F(p1, p2);
        Vector3F v13=new Vector3F(p1, p3);
        Vector3F n=v12.cross(v13);
        float A=n.x(); float x0=p1.x();
        float B=n.y(); float y0=p1.y();
        float C=n.z(); float z0=p1.z();
        int y1,y2,y3,x1,x2,x3,temp, xs, xend;
        y1=(int)p1.y(); y2=(int)p2.y(); y3=(int)p3.y();
        x1=(int)p1.x(); x2=(int)p2.x(); x3=(int)p3.x();
        if(y1>y2){
            temp=y1; y1=y2;y2=temp;
            temp=x1; x1=x2;x2=temp;
        }
        if(y2>y3){
            temp=y2; y2=y3;y3=temp;
            temp=x2; x2=x3;x3=temp;
        }
        if(y1>y2){
            temp=y1; y1=y2;y2=temp;
            temp=x1; x1=x2;x2=temp;
        }
        float dx1,dy1, dx2,dy2, dx3,dy3, z;
        dy1=y2-y1; dx1=x2-x1;
        dy2=y3-y2; dx2=x3-x2;
        dy3=y3-y1; dx3=x3-x1;
        float grad1=dx1/dy1;
        float grad2=dx2/dy2;
        float grad3=dx3/dy3;
        int a1=y1*width();
        int a2=0;
        for(int y=y1;y<y2;y++){
            xs=(int) (x1+grad1*(y-y1));
            xend=(int) (x3+grad3*(y-y3));
            if(xs>xend){
                temp=xs; xs=xend;xend=temp;
            }
            a2=a1+xs;
            for(int x=xs; x<xend;x++){
                z=z0-(A*(x-x0)+B*(y-y0))/C;
                if(z>zBuffer.getDepth(x,y)){
                    zBuffer.setAdress(a2,z);
                    buffer.setAdress(a2,color);
                }
                a2++;
            }
            a1=a1+width();
        }
        for(int y=y2;y<y3;y++){
            xs=(int) (x2+grad2*(y-y2));
            xend=(int) (x3+grad3*(y-y3));
            if(xs>xend){
                temp=xs; xs=xend;xend=temp;
            }
            a2=a1+xs;
            for(int x=xs; x<xend;x++){
                z=z0-(A*(x-x0)+B*(y-y0))/C;
                if(z>zBuffer.getDepth(x,y)){
                    zBuffer.setAdress(a2,z);
                    buffer.setAdress(a2,color);
                }
                a2++;
            }
            a1=a1+width();
        }
    }
    public Point3F[][] toTriangles(Point3F[] p){
        Point3F[][] triangles=new Point3F[p.length-2][3];
        for(int i=0;i<p.length-2;i++){
            triangles[i]=new Point3F[]{p[0], p[i+1], p[i+2]};
        }
        return triangles;
    }

    public int clipLeft(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x, y, z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=x1>=-1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=x2>=-1;
            if(inside1!=inside2){
                if(dx==0){
                    y=y2;
                    z=z2;
                }
                else {
                    y=y2+dy*(-1-x2)/dx;
                    z=z2+dz*(-1-x2)/dx;
                }
                x=-1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    public int clipRight(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x,y,z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=x1<=1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=x2<=1;
            if(inside1!=inside2){
                if(dx==0){
                    y=y2;
                    z=z2;
                }
                else {
                    y=y2+dy*(1-x2)/dx;
                    z=z2+dz*(1-x2)/dx;
                }
                x=1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    public int clipUp(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x,y,z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=y1<=1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=y2<=1;
            if(inside1!=inside2){
                if(dy==0){
                    x=x2;
                    z=z2;
                }
                else {
                    x=x2+dx*(1-y2)/dy;
                    z=z2+dz*(1-y2)/dy;
                }
                y=1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    public int clipDown(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x,y,z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=y1>=-1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=y2>=-1;
            if(inside1!=inside2){
                if(dy==0){
                    x=x2;
                    z=z2;
                }
                else {
                    x=x2+dx*(-1-y2)/dy;
                    z=z2+dz*(-1-y2)/dy;
                }
                y=-1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    public int clipForward(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x,y,z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=z1<=1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=z2<=1;
            if(inside1!=inside2){
                if(dz==0){
                    x=x2;
                    y=y2;
                }
                else {
                    x=x2+dx*(1-z2)/dz;
                    y=y2+dy*(1-z2)/dz;
                }
                z=1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    public int clipBack(int len, Point3F[] p1, Point3F[] p2){
        float x1, y1, x2, y2, z1, z2, dx, dy, dz, x,y,z;
        boolean inside1, inside2;
        x1=p1[len-1].x();
        y1=p1[len-1].y();
        z1=p1[len-1].z();
        inside1=z1>=-1;
        int index=0;
        for(int i=0; i<len;i++){
            x2=p1[i].x();
            y2=p1[i].y();
            z2=p1[i].z();
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            inside2=z2>=-1;
            if(inside1!=inside2){
                if(dz==0){
                    x=x2;
                    y=y2;
                }
                else {
                    x=x2+dx*(-1-z2)/dz;
                    y=y2+dy*(-1-z2)/dz;
                }
                z=-1;
                p2[index]=new Point3F(x, y, z);
                index++;
            }
            if(inside2){
                p2[index]=p1[i];
                index++;
            }
            x1=x2; y1=y2; z1=z2;
            inside1=inside2;
        }
        return index;
    }
    /*
    public Point3F[] clip(Triangle triangle){

    }

     */



    public void clear(){
        buffer.clear();
        zBuffer.clear();
    }
    public PixelBuffer buffer(){
        return buffer;
    }
    public int width(){
        return buffer.width();
    }
    public int height(){
        return buffer.height();
    }
}
