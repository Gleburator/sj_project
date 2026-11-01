package geometry.base;


import linearalgebra.MatrixF;
import linearalgebra.QuaternionF;
import linearalgebra.Vector4F;
import static mathematics.MathF.*;

public final class Vector3F extends Vector {
    private final float x;
    private final float y;
    private final float z;
    public Vector3F() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3F(float[] v){
        this.x=v[0];
        this.y=v[1];
        this.z=v[2];
    }
    public Vector3F(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3F(Point3F p1, Point3F p2) {
        this.x = p2.x() - p1.x();
        this.y = p2.y() - p1.y();
        this.z = p2.z() - p1.z();
    }
    public float x()
    {
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
    public MatrixF toMatrix() {
        MatrixF m = new MatrixF(3, 1);
        m.set(0, 0, x);
        m.set(1, 0, y);
        m.set(2, 0, z);
        return m;
    }
    public Vector3F add(Vector3F v) {
        float x2, y2, z2;
        x2 = x + v.x();
        y2 = y + v.y();
        z2 = z + v.z();
        return new Vector3F(x2, y2, z2);
    }
    public Vector3F sub(Vector3F v) {
        float x2, y2, z2;
        x2 = x - v.x();
        y2 = y - v.y();
        z2 = z - v.z();
        return new Vector3F(x2, y2, z2);
    }
    public float dot(Vector3F v) {
        return x*v.x()+ y*v.y() + z*v.z();
    }
    public Vector3F multiply(float lambda) {
        float x2, y2, z2;
        x2 = x *lambda;
        y2 = y *lambda;
        z2 = z *lambda;
        return new Vector3F(x2, y2, z2);
    }
    public Vector3F division(float lambda) {
        float x2, y2, z2;
        x2 = x /lambda;
        y2 = y /lambda;
        z2 = z /lambda;
        return new Vector3F(x2, y2, z2);
    }
    public Vector3F cross(Vector3F v) {
        float x2, y2, z2;
        x2 = (y * v.z() - v.y() * z);
        y2 = -(x * v.z() - v.x() * z);
        z2 = (x * v.y() - v.x() * y);
        return new Vector3F(x2, y2, z2);
    }
    public float length() {
        return sqrt(squaredLength());
    }
    public float squaredLength() {
        return x * x + y * y + z * z;
    }


    public Vector3F negative(){
        return new Vector3F(-x,-y,-z);
    }
    public Vector3F normalize(){
        return new Vector3F(x/length(),y/length(),z/length());
    }


    public float vectorAngle(Vector3F v) {
        float angle, scalar, length;
        length=this.length();
        if ((length * v.length()) != 0) {
            scalar = this.dot(v);
            angle = acos(scalar / (length * v.length()));
            if(angle>PI){

                angle=PI-angle;
            }
        }
        else {
            angle = 0;
        }
        return angle;
    }
    public boolean equals(Vector3F v) {
        if (v.x() == x && v.y() == y && v.z() == z) {
            return true;
        }
        else {
            return false;
        }
    }
    public String toString() {
        String s = "[" + x + " ," + y + " ," + z + "]";
        return s;
    }
    public Point3F toPoint3F(){
        return new Point3F(x,y,z);
    }
    public Vector4F toVector4F(){
        return new Vector4F(x,y,z,0);
    }
    public Vector3F rotated(QuaternionF q){
        Vector3F v=new Vector3F(x,y,z);
        QuaternionF q1= q.multiply(v).multiply(q.invert());
        return new Vector3F(q1.x(),q1.y(),q1.z());
    }
    public Vector2F projected(){
        return new Vector2F(x,y);
    }
    public final static Vector3F X=new Vector3F(1,0,0);
    public final static Vector3F Y=new Vector3F(0,1,0);
    public final static Vector3F Z=new Vector3F(0,0,1);

}
