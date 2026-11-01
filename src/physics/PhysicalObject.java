package physics;

import geometry.base.Object3;
import geometry.base.Vector3F;

public class PhysicalObject {
    private final float mass;
    private final Object3 body;
    private Vector3F velocity;
    private Vector3F acceleration;
    public PhysicalObject(Object3 body, float mass){
        this.mass=mass;
        this.body=body;
        this.velocity=new Vector3F();
        this.acceleration=new Vector3F();
    }
    public void setVelocity(Vector3F v){
        this.velocity=v;
    }
    public void setAcceleration(Vector3F a){
        this.acceleration=a;
    }
    public Vector3F acceleration(){
        return acceleration;
    }
    public Vector3F velocity(){
        return velocity;
    }


    public Object3 body(){
        return body;
    }
    public float mass(){
        return mass;
    }
}
