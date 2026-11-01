package geometry.base;
import static mathematics.MathF.*;
import java.util.Comparator;

public final class Point2F extends Point{
    private final float x;
    private final float y;
    public Point2F(float[] p){
        this.x=p[0];
        this.y=p[1];
    }
    public Point2F(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float x()
    {
        return x;
    }
    public float y()
    {
        return y;
    }
    public Point2F add(Vector3F v){
        return new Point2F(x+v.x(), y+v.y());
    }
    public Point2F sub(Vector3F v){
        return new Point2F(x-v.x(), y-v.y());
    }
    public float squaredDist(Point2F p)
    {
        float x, y;
        x = this.x - p.x();
        y = this.y - p.y();
        return x * x + y * y;
    }
    public float dist(Point2F p) {
        return sqrt(squaredDist(p));
    }
    public String toString() {
        String s = "{" + x + ", " + y + "}";
        return s;
    }
    public Point2F toVector2F(){
        return new Point2F(x,y);
    }
    public static final Comparator<Point2F> COMPARE_Y=new Comparator<Point2F>() {
        @Override
        public int compare(Point2F p1, Point2F p2) {
            return Double.compare(p1.y(), p2.y());
        }
    };
    public static final Comparator<Point2F> COMPARE_X=new Comparator<Point2F>() {
        @Override
        public int compare(Point2F p1, Point2F p2) {
            return Double.compare(p1.x(), p2.x());
        }
    };
}

