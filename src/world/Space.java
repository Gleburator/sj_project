package world;

import geometry.base.Point3F;
import geometry.base.Vector3F;
import physics.PhysicalObject;

public class Space {
    private final PhysicalObject[] space;
    private final Camera camera;
    private final float ambient;
    private final LightSource[] sources;
    public Space(PhysicalObject... objects){
        this.space=objects;
        Point3F center=new Point3F(0,0,3000);
        Vector3F direction=new Vector3F(0,0,-1);
        this.camera=new Camera(center,direction);
        this.ambient=0.5f;
        this.sources=new LightSource[] {new LightSource(center, direction)};

    }
    public float ambient(){
        return ambient;
    }
    public LightSource lightSource(int i){
        return sources[i];
    }
    public Camera camera(){
        return camera;
    }
    public int count(){
        return space.length;
    }
    public PhysicalObject get(int i){
        return space[i];
    }
    public void set(int i, PhysicalObject object){
        space[i]=object;
    }
}
