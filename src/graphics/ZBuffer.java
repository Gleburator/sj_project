package graphics;

import java.util.Arrays;

public class ZBuffer {
    private float[] zBuffer;
    private final int width;
    private final int height;
    public ZBuffer(int width, int height){
        this.width=width;
        this.height=height;
        this.zBuffer=new float[width*height];
        Arrays.fill(zBuffer, -Float.MAX_VALUE);
    }
    public float getDepth(int x, int y){
        return zBuffer[y*width+x];
    }
    public void setDepth(int x, int y, float depth){
        zBuffer[y*width+x]=depth;
    }
    public void setAdress(int i, float depth){
        zBuffer[i]=depth;
    }

    public int width(){
        return width;
    }
    public int height(){
        return height;
    }
    public void clear(){
        Arrays.fill(zBuffer, -Float.MAX_VALUE);
    }

}
