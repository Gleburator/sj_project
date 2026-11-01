
package linearalgebra;

import geometry.base.Point;
import geometry.base.Point3F;

public final class Point4F extends Point {
    private final float x, y, z, w;
    public Point4F(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float x() {
        return x;
    }
    public float y()
    {
        return y;
    }
    public float z()
    {
        return z;
    }
    public float w(){
        return w;
    }
    public String toString()
    {
        String s = "{" + x + ", " + y + ", " + z + ", " + w+ "}"; ;
        return s;
    }
    public Point3F toPoint3F(){
        return new Point3F(x/w,y/w,z/w);
    }
}

