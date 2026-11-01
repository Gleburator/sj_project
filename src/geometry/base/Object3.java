package geometry.base;

import geometry.shape.Polygon;
import geometry.shape.Polyhedron;
import linearalgebra.MatrixF;

import java.util.Arrays;
import java.util.Comparator;

public final class Object3 {
    private final Triangle[] obj;
    public Object3(Polyhedron polyhedron){
        this.obj=polyhedron.toTriangles();
    }
    public Object3(Polygon polygon){
        this.obj= polygon.toTriangles();
    }
    public Object3(Triangle[] triangles){
        this.obj=triangles;
    }
    public Triangle get(int i){
        return obj[i];
    }
    public int count(){
        return obj.length;
    }
    public Object3 rotated(float alpha, float beta, float gamma){
        MatrixF m= MatrixF.rotationMatrix(alpha, beta, gamma);
        Triangle[] triangles=new Triangle[count()];
        for(int i=0; i<count();i++){
            triangles[i]=this.obj[i].transformed(m);
        }
        return new Object3(triangles);
    }
    public Object3 sorted(Comparator<Triangle> comparator){
        Triangle[] triangles=Arrays.copyOf(this.obj, count());
        Arrays.sort(triangles, comparator);
        return new Object3(triangles);
    }
    public Object3 moved(float x, float y, float z){
        MatrixF m= MatrixF.translationMatrix(x, y, z);
        Triangle[] triangles=new Triangle[count()];
        for(int i=0; i<count();i++){
            triangles[i]=this.obj[i].transformed(m);
        }
        return new Object3(triangles);
    }
    public Object3 transformed(MatrixF m){
        Triangle[] triangles =new Triangle[count()];
        for(int i=0; i<count(); i++){
            triangles[i]=this.obj[i].transformed(m);
        }
        return new Object3(triangles);
    }
    public String toString(){
        return Arrays.deepToString(obj);
    }
}
