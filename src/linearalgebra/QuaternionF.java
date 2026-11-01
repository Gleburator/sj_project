package linearalgebra;

import geometry.base.Point3F;
import geometry.base.Vector3F;
import static mathematics.MathF.*;
public final class QuaternionF extends Quaternion{
    private final float w;
    private final float x;
    private final float y;
    private final float z;
    public QuaternionF(float w, float x, float y, float z){
        this.w=w;
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public QuaternionF(QuaternionF q){
        this.w=q.w();
        this.x=q.x();
        this.y=q.y();
        this.z=q.z();
    }
    public static QuaternionF angle(float theta, Vector3F n){
        float w=cos(theta/2);
        float x=n.x()*sin(theta/2);
        float y=n.y()*sin(theta/2);
        float z=n.z()*sin(theta/2);
        return new QuaternionF(w,x,y,z);
    }

    public QuaternionF(float w, Vector3F V){
        this.w=w;
        this.x=V.x();
        this.y=V.y();
        this.z=V.z();
    }
    public QuaternionF(float w, Point3F p){
        this.w=w;
        this.x=p.x();
        this.y=p.y();
        this.z=p.z();
    }
    public float w(){
        return w;
    }
    public float x(){
        return x;
    }
    public float y(){
        return y;
    }
    public float z(){
        return z;
    }
    public Vector3F V(){
        return new Vector3F(x,y,z);
    }
    public float squaredLength(){
        return w*w+x*x+y*y+z*z;
    }
    public float length(){
        return sqrt(squaredLength());
    }
    public QuaternionF conj(){
        return new QuaternionF(w, -x,-y,-z);
    }
    public QuaternionF normalize(){
        return new QuaternionF(w/length(), x/length(), y/length(), z/length());
    }
    public QuaternionF division(float lambda){
        return new QuaternionF(w/lambda,x/lambda,y/lambda,z/lambda);
    }
    public QuaternionF multiply(float lambda){
        return new QuaternionF(w*lambda,x*lambda,y*lambda,z*lambda);
    }
    public QuaternionF invert(){
        return conj().division(squaredLength());
    }
    public QuaternionF pow(int n){
        QuaternionF q=new QuaternionF(w,x,y,z);
        for(int i=1; i<n;i++){
            q=q.multiply(q);
        }
        return q;
    }
    public QuaternionF multiply(QuaternionF q){
        Vector3F V1=this.V();
        Vector3F V2=q.V();
        float w1=this.w;
        float w2=q.w();
        float w=w1*w2-V1.dot(V2);
        Vector3F V=V2.multiply(w1).add(V1.multiply(w2)).add(V1.cross(V2));
        return new QuaternionF(w,V);
    }
    public QuaternionF multiply(Point3F p){
        Vector3F V1=this.V();
        Vector3F V2=p.toVector3F();
        float w=-V1.dot(V2);
        Vector3F V=V2.multiply(this.w).add(V1.cross(V2));
        return new QuaternionF(w,V);
    }
    public QuaternionF multiply(Vector3F v){
        Vector3F V1=this.V();
        Vector3F V2=v;
        float w=-V1.dot(V2);
        Vector3F V=V2.multiply(this.w).add(V1.cross(V2));
        return new QuaternionF(w,V);
    }
    public String toString() {
        String s = "{"+ w+ " [" + x + ", " + y + ", " + z + "]"+"}";
        return s;
    }
}
