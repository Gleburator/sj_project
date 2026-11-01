package graphics;

import geometry.base.Point2F;
import geometry.base.Vector2F;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import static mathematics.MathF.*;
import static mathematics.MathF.fpart;
import static mathematics.MathF.ipart;
import static mathematics.MathF.max;
import static mathematics.MathF.min;
import static mathematics.MathF.sign;

public class PixelBuffer {
    private final BufferedImage image;
    private final int[] buffer;
    private final int width;
    private final int height;
    private final ColorARGB background;
    public PixelBuffer(int width, int height){
        this.width=width;
        this.height = height;
        this.image=new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        this.buffer = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        this.background=ColorARGB.WHITE;
        Arrays.fill(buffer, background.toInt());
    }


    public void drawLine(int x1, int y1, int x2, int y2, ColorARGB color) {
        int dx, dy;
        dx = abs(x2 - x1);
        dy = abs(y2 - y1);
        if(dx==0)
            drawVertical(x1,y1,x2,y2,color);
        else if(dy==0)
            drawHorizontal(x1,y1,x2,y2,color);
        else {
            int error1, error2, signX, signY;
            if (x1<x2)
                signX = 1;
            else
                signX = -1;
            if(y1 < y2)
                signY = 1;
            else
                signY = -1;
            error1 = dx - dy;
            while(x1!=x2||y1!=y2) {
                this.setColor(x1, y1, color);
                error2 = error1 * 2;
                if(error2>-dy) {
                    error1 = error1 - dy;
                    x1 = x1 + signX;
                }
                if(error2<dx) {
                    error1 = error1 + dx;
                    y1 = y1 + signY;
                }
            }
        }
    }
    public void drawHorizontal(int x1, int y1, int x2, int y2, ColorARGB color){
        if(y1!=y2){
            throw new IllegalArgumentException("Ординаты должны быть равны");
        }
        else {
            int signX;
            if (x1<x2)
                signX=1;
            else
                signX=-1;
            this.setColor(x1, y1,color);
            while (x1!=x2){
                x1=x1+signX;
                this.setColor(x1, y1,color);
            }
        }
    }
    public void drawVertical(int x1, int y1, int x2, int y2, ColorARGB color){
        if(x1!=x2){
            throw new IllegalArgumentException("Абциссы должны быть равны");
        }
        else {
            int signY;
            if (y1<y2)
                signY=1;
            else
                signY=-1;
            this.setColor(x1, y1,color);
            while (y1!=y2){
                y1=y1+signY;
                this.setColor(x1, y1,color);
            }
        }
    }


    public void drawLineWu(int x1, int y1, int x2, int y2, ColorARGB color) {
        float dx, dy;
        dx = abs(x2 - x1);
        dy = abs(y2 - y1);
        if(dx==0)
            drawVertical(x1,y1,x2,y2,color);
        else if(dy==0)
            drawHorizontal(x1,y1,x2,y2,color);
        else {
            int signX, signY;
            if (x1<x2)
                signX = 1;
            else
                signX = -1;
            if(y1 < y2)
                signY = 1;
            else
                signY = -1;
            float gradient;
            if(dx>dy){
                float y=y1;
                gradient=dy/dx*signY;
                while (x1!=x2){
                    this.setColor(x1, ipart(y), color.blend(background, 1 - fpart(y)));
                    this.setColor(x1, ipart(y)+ 1, color.blend(background, fpart(y)));
                    x1=x1+signX;
                    y=y+gradient;
                }
            }
            else {
                float x=x1;
                gradient=dx/dy*signX;
                while (y1!=y2){
                    this.setColor(ipart(x), y1, color.blend(background, 1 - fpart(x)));
                    this.setColor(ipart(x)+1, y1, color.blend(background, fpart(x)));
                    y1=y1+signY;
                    x=x+gradient;
                }
            }

        }
    }

    public void drawCircle(float xc, float yc, float radius, ColorARGB color){
        int x, y, d, x0, y0, R, error;
        x0=(int) xc;
        y0=(int) yc;
        R=(int) radius;
        x = 0;
        y = R;
        d = 1 - 2 * R;
        while(y>=0) {
            this.setColor(x0 + x,y0 + y, color);
            this.setColor( x0 - x,y0 + y, color);
            this.setColor( x0 + x,y0 - y, color);
            this.setColor( x0 - x,y0 - y, color);
            error = 2 * (d + y) - 1;
            if(d<0&&error<=0) {
                x++;
                d = d + 2 * x + 1;
                continue;
            }
            if(d>=0&&error>0) {
                y--;
                d = d + 1 - 2 * y;
                continue;
            }
            x++;
            d = d + 2 * (x - y);
            y--;
        }
    }

    public void fillCircle(float xc, float yc, float radius, ColorARGB color){
        int x, y, d, x0, y0, R, error;
        x0=(int) xc;
        y0=(int) yc;
        R=(int) radius;
        x = 0;
        y = R;
        d = 1 - 2 * R;
        while(y>=0) {
            drawHorizontal(x0 + x,y0 + y,x0 - x,y0 + y, color);
            drawHorizontal(x0 - x,y0 - y,x0 + x,y0 - y, color);
            error = 2 * (d + y) - 1;
            if(d<0&&error<=0) {
                x++;
                d = d + 2 * x + 1;
                continue;
            }
            if(d>=0&&error>0) {
                y--;
                d = d + 1 - 2 * y;
                continue;
            }
            x++;
            d = d + 2 * (x - y);
            y--;
        }
    }
    public void drawSemiCircle(float cx, float cy, float radius, boolean isUp, ColorARGB color){
        int x, y, d, x0, y0, R, error;
        x0=(int) cx;
        y0=(int) cy;
        R=(int) radius;
        x = 0;
        y = R;
        d = 1 - 2 * R;
        while (y >= 0) {
            if(isUp) {
                this.setColor(x0 + x, y0 - y, color);
                this.setColor(x0 - x, y0 - y,  color);
            }
            else {
                this.setColor( x0 + x,y0 + y, color);
                this.setColor(x0 - x, y0 + y,  color);
            }
            error = 2 * (d + y) - 1;
            if (d < 0 && error <= 0) {
                x++;
                d = d + 2 * x + 1;
                continue;
            }
            if (d >= 0 && error > 0) {
                y--;
                d = d + 1 - 2 * y;
                continue;
            }
            x++;
            d = d + 2 * (x - y);
            y--;
        }
    }
    public void drawPolygon(ColorARGB color, Point2F... points) {
        Point2F p1;
        Point2F p2;
        for (int i=0; i<points.length; i++) {
            p1=points[i];
            if (i==points.length-1) {
                p2=points[0];
                this.drawLineWu((int)p1.x(), (int)p1.y() ,(int)p2.y(),(int)p2.y(), color);
            }
            else {
                p2=points[i+1];
                this.drawLineWu((int)p1.x(), (int)p1.y() ,(int)p2.y(),(int)p2.y(), color);
            }
        }
    }
    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, ColorARGB color) {
        this.drawLineWu(x1 ,y1,x2,y2, color);
        this.drawLineWu(x2 ,y2,x3,y3, color);
        this.drawLineWu(x3 ,y3,x1,y1, color);
    }
    public void drawPoint(int x, int y, ColorARGB color){
        this.setColor(x, y, color);
    }

    
    
    
    
    public int getColor(int x, int y){
        return buffer[y*width+x];
    }
    public void setColor(int x, int y, ColorARGB color){
        buffer[y*width+x]=color.toInt();
    }
    public void setAdress(int i, ColorARGB color){
        buffer[i]=color.toInt();
    }
    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public Image toImage(){
        return image;
    }
    public void clear(){
        Arrays.fill(buffer, background.toInt());
    }

}
