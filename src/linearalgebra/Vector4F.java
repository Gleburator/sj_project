
package linearalgebra;


import geometry.base.Vector;

public final class Vector4F extends Vector {
    private final float x, y, z, w;
    public Vector4F() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }
    public Vector4F(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Vector4F(Point4F p1, Point4F p2) {
        this.x = p2.x() - p1.x();
        this.y = p2.y() - p1.y();
        this.z = p2.z() - p1.z();
        this.w = p2.w()- p1.w();
    }
    public float x() {
        return x;
    }
    public float y() {
        return y;
    }
    public float z() {
        return z;
    }
    public float w(){
        return w;
    }
    public String toString() {
        String s = "[" + x + ", " + y + ", " + z + ", " + w + "]"; ;
        return s;
    }

}

