/*
package graphics;

public class Algorithms {
public int code(Point3F p){
        int code=0;
        if(p.x()<-1)
            code++;
        else if(p.x()>1)
            code+=2;
        if(p.y()<-1)
            code+=4;
        else if(p.y()>1)
            code+=8;
        if(p.z()<-1)
            code+=16;
        else if(p.z()>1)
            code+=32;
        return code;
    }
    public int code(float x, float y, float z){
        int code=0;
        if(x<-1)
            code++;
        else if(x>1)
            code+=2;
        if(y<-1)
            code+=4;
        else if(y>1)
            code+=8;
        if(z<-1)
            code+=16;
        else if(z>1)
            code+=32;
        return code;
    }
    //>>8&0xFF
}
public Point3F[] cohen_sutherland(Point3F p1, Point3F p2){
        float x1, y1, z1, x2, y2, z2, x, y, z;
        int code1 = code(p1);
        int code2 = code(p2);
        x1 = p1.x();
        y1 = p1.y();
        z1 = p1.z();
        x2 = p1.x();
        y2 = p2.y();
        z2 = p2.z();
        float dx, dy, dz;
        boolean inside = (code1 | code2) == 0;
        while (!inside && (code1 & code2) == 0) {
            if (code1 == 0) {
                x = x1; x1 = x2; x2 = x;
                y = y1; y1 = y2; y2 = y;
                z = z1; z1 = z2; z2 = z;
                code2 = code1;
            }
            dx=x2-x1; dy=y2-y1; dz=z2-z1;
            if (x1 < -1) {
                x1 = -1;
                y1 = y1 + dy * (-1 - x1) / dx;
                z1=z1+(z2-z1)*(-1 - x1)/ dx;
            }
            else if (x1 > 1) {
                x1 = 1;
                y1 = y1 + dy * (1 - x1) / dx;
                z1=  z1+(z2-z1)*(1 - x1)/ dx;
            }
            else if (y1 < -1) {
                x1 = x1 + dx * (-1 - y1) / dy;
                y1 = -1;
                z1=  z1+(z2-z1)*(-1 - y1)/ dy;
            }
            else if (y1 > 1) {
                x1 = x1 + dx * (1 - y1) / dy;
                y1 = 1;
                z1=  z1+(z2-z1)*(1 - y1)/ dy;
            }
            else if(z1<-1){
                x1 = x1 + dx * (-1 - z1) / dz;
                y1 = y1 + dy * (-1 - z1) / dz;
                z1=  -1;
            }
            else if(z1>1){
                x1 = x1 + dx * (-1 - z1) / dz;
                y1 = y1 + dy * (-1 - z1) / dz;
                z1 = 1;
            }
            code1=code(x1,y1,z1);
            inside=(code1&code2)==0;
            }
        if(inside){
            return new Point3F[]{new Point3F(x1,y1,z1), new Point3F(x2,y2,z2)};
        }
        else {
            return null;
        }
    }
 */
