package world;

import geometry.base.Point3F;
import geometry.base.Vector3F;

public class LightSource {
    Point3F center;
    Vector3F direction;
    public LightSource(Point3F center, Vector3F direction){
        this.center=center;
        this.direction=direction.normalize();
    }

}
