package world;

import geometry.base.Point3F;
import geometry.base.Vector3F;

import static mathematics.MathF.*;

public final class Camera {
    private Point3F center;
    private Vector3F direction;
    private float pitch;
    private float yaw;
    private float roll;
    private float speed;
    public Camera(Point3F center, Vector3F direction){
        this.center=center;
        this.direction=direction.normalize();
        this.pitch=atan(direction.y()/direction.z());
        this.yaw= atan2(direction.z(),direction.x());
        this.roll=0;
        //System.out.println(pitch+" "+yaw+" "+roll);
        this.speed=0;
    }

    public void move(float x, float y, float z){
        Vector3F v=new Vector3F(x,y,z);
        this.center=center.add(v);
    }
    public void moveForward(){
        Vector3F v=direction.multiply(speed);
        this.center=center.add(v);

    }
    public void moveBack(){
        Vector3F v=direction.multiply(speed);
        this.center=center.sub(v);
    }
    public void moveRight(){
        Vector3F v=direction.cross(Vector3F.Y).multiply(speed);
        this.center=center.add(v);
    }
    public void moveLeft(){
        Vector3F v=direction.cross(Vector3F.Y).multiply(speed);
        this.center=center.sub(v);
    }
    public void moveUp(){
        Vector3F v= Vector3F.Y.multiply(speed);
        this.center=center.add(v);
    }
    public void moveDown(){
        Vector3F v= Vector3F.Y.multiply(speed);
        this.center=center.sub(v);
    }

    public void rotate(float alpha, float beta, float gamma){

        if(abs(pitch-alpha)>PI/2){
            this.pitch=sign(pitch)*PI/2;
        }
        else {
            this.pitch=pitch-alpha;
        }
        this.yaw=yaw+beta;
        this.roll=roll+gamma;
        float x=cos(yaw)*cos(pitch);
        float y=sin(pitch);
        float z=sin(yaw)*cos(pitch);
        this.direction=new Vector3F(x,y,z);

    }
    public Vector3F direction(){
        return direction;
    }
    public Point3F center(){
        return center;
    }
    public void setSpeed(float speed){
        this.speed=speed;
    }
    public void addSpeed(float speedBonus){
        if(speed+speedBonus>=0){
            this.speed=this.speed+speedBonus;
        }
    }
}
