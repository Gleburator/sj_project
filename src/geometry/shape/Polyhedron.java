package geometry.shape;

import extension.Printing;
import geometry.base.Point3F;
import geometry.base.Triangle;
import linearalgebra.MatrixF;

public class Polyhedron extends Figure {
    protected Polygon[] polygons;
    public Polyhedron(Point3F center, Polygon... polygons){
        super(center);
        this.polygons=polygons;
    }
    public Polyhedron(Polygon... polygons){
        float x=0;
        float y=0;
        float z=0;
        for(int i=0; i< polygons.length;i++){
            x=x+polygons[i].center().x();
            y=y+polygons[i].center().y();
            z=z+polygons[i].center().z();
        }
        x=x/polygons.length;
        y=y/ polygons.length;
        z=z/ polygons.length;
        Point3F center=new Point3F(x,y,z);
        super(center);
        this.polygons=polygons;
    }
    public Triangle[] toTriangles(){
        int count=0;
        for(int i=0; i<count(); i++){
            count=count+ polygons[i].count()-2;
        }
        Triangle[] triangles=new Triangle[count];
        Triangle[] tri;
        int index=0;
        for(int i=0; i<count(); i++){
            tri= polygons[i].toTriangles();
            for(int j=0; j<tri.length; j++){
                triangles[index]=tri[j];
                index++;
            }
        }
        return triangles;
    }
    public void rotate(float alpha, float beta, float gamma){
        MatrixF m= MatrixF.rotationMatrix(alpha, beta, gamma);
        for (int i = 0; i < count(); i++) {
            polygons[i].transform(m);
        }
    }

    public Polyhedron rotated(float alpha, float beta, float gamma){
        MatrixF m= MatrixF.rotationMatrix(alpha, beta, gamma);
        Polygon[] polygons=new Polygon[count()];
        for (int i = 0; i < count(); i++) {
            polygons[i]= this.polygons[i].transformed(m);
        }
        return new Polyhedron(center, polygons) ;
    }
    public Polyhedron transformed(MatrixF m){
        Polygon[] polygons=new Polygon[count()];
        for (int i = 0; i < count(); i++) {
            polygons[i]= this.polygons[i].transformed(m);
        }
        return new Polyhedron(center, polygons) ;
    }
    public void move(float x, float y, float z){
        MatrixF m= MatrixF.translationMatrix(x,y,z);
        for (int i = 0; i < count(); i++) {
            polygons[i].transform(m);
        }
    }
    public Polyhedron moved(float x, float y, float z){
        MatrixF m= MatrixF.translationMatrix(x,y,z);
        Polygon[] polygons=new Polygon[count()];
        Point3F center=m.multiply(this.center);
        for (int i = 0; i < count(); i++) {
            polygons[i]=this.polygons[i].transformed(m);
        }
        return new Polyhedron(center, polygons);
    }
    public int count(){
        return polygons.length;
    }
    public Polygon polygon(int i){
        return polygons[i];
    }
}
