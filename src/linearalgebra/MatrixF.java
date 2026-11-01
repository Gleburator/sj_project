package linearalgebra;
import world.Camera;
import geometry.base.Point3F;
import geometry.base.Vector2F;
import geometry.base.Vector3F;

import static mathematics.MathF.*;
public class MatrixF extends Matrix {
    private final float[][] m;
    public MatrixF(int row, int column) {
        super(row, column);
        this.m = new float[row][column];
    }

    public MatrixF(int size) {
        super(size);
        this.m = new float[size][size];
    }

    public MatrixF(float[][] m) {
        super(m.length, m[0].length);
        this.m = m.clone();
    }

    public MatrixF(MatrixF m) {
        super(m.row(), m.column());
        this.m = new float[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.m[i][j]=m.get(i, j);
            }
        }
    }
    public boolean equals(MatrixF m1) {
        boolean isEqual = true;
        boolean flag = true;
        if (row == m1.row() && column == m1.column()) {
            for (int i = 0; i < m1.row(); i++) {
                if (flag) {
                    for (int j = 0; j < column; j++) {
                        if (!(m[i][j] ==m1.get(i, j))) {
                            isEqual = false;
                            flag = false;
                            break;
                        }
                    }
                }

            }
        } else {
            isEqual = false;
        }
        return isEqual;
    }

    public void set(int i, int j, float x) {
        m[i][j] =x;
    }
    public float get(int i, int j) {
        return m[i][j];
    }
    public String toString() {
        StringBuilder s = new StringBuilder(2*row*column);
        s.append("(");
        String indent;
        String mij;
        int length;
        int max;
        int[] maxColumn = new int[column];
        for (int j = 0; j < column; j++) {
            max = 0;
            for (int i = 0; i < row; i++) {
                mij = Float.toString(m[i][j]);
                if (mij.length() > max) {
                    max = mij.length();
                }
            }
            maxColumn[j] = max;
        }
        for (int i = 0; i < row; i++) {
            if (i != 0) {
                s.append(" ");
            }
            for (int j = 0; j < column; j++) {
                mij = Float.toString(m[i][j]);
                if (j == column - 1) {
                    s.append(mij);
                } else {
                    length = maxColumn[j] - mij.length();
                    indent = " ";
                    for (int k = 0; k < length; k++) {
                        indent = indent + " ";
                    }
                    s.append(mij);
                    s.append(indent);
                }
            }
            if (i != row - 1) {
                s.append("\n");
            }
        }
        s.append(")");
        return s.toString();
    }
    public MatrixF transpose() {
        MatrixF m = new MatrixF(column, row);
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                m.set(i, j, this.m[j][i]);
            }
        }
        return m;
    }
    public MatrixF add(MatrixF m1) {
        if (m1.column() == column && m1.row() == row) {
            MatrixF m2 = new MatrixF(m1.row(), m1.column());
            for (int i = 0; i < m1.row(); i++) {
                for (int j = 0; j < m1.column(); j++) {
                    m2.set(i, j, m[i][j]+m1.get(i, j));
                }
            }
            return m2;
        }
        else {
            throw new IllegalArgumentException("Сложение матриц разных размеров.");
        }
    }
    public MatrixF sub(MatrixF m1) {
        if (m1.column() == column && m1.row() == row) {
            MatrixF m2 = new MatrixF(m1.row(), m1.column());
            for (int i = 0; i < m1.row(); i++) {
                for (int j = 0; j < m1.column(); j++) {
                    m2.set(i, j, m[i][j]-m1.get(i, j));
                }
            }
            return m2;
        }
        else {
            throw new IllegalArgumentException("Сложение матриц разных размеров.");
        }
    }
    public MatrixF multiply(float x) {
        MatrixF m1 = new MatrixF(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                m1.set(i, j, m[i][j]*x);
            }
        }
        return m1;
    }
    public MatrixF multiply(MatrixF m1) {
        float c;
        if (column == m1.row()) {
            MatrixF m2 = new MatrixF(row, m1.column());
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < m1.column(); j++) {
                    c = 0;
                    for (int s = 0; s < column; s++) {
                        c =c+m[i][s]*m1.get(s, j);
                    }
                    m2.set(i, j, c);
                }
            }
            return m2;
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }
    public Vector3F multiply(Vector3F v) {
        if (column == 4) {
            float x = v.x() * m[0][0] + v.y() * m[0][1] + v.z() * m[0][2];
            float y = v.x() * m[1][0] + v.y() * m[1][1] + v.z() * m[1][2];
            float z = v.x() * m[2][0] + v.y() * m[2][1] + v.z() * m[2][2];
            return new Vector3F(x, y, z);
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }
    public Vector2F multiply(Vector2F v) {
        if (column == 3) {
            float x = v.x() * m[0][0] + v.y() * m[0][1];
            float y = v.x() * m[1][0] + v.y() * m[1][1];
            return new Vector2F(x, y);
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }


    public Point3F multiply(Point3F p) {
        if (column == 4) {
            float x = p.x() * m[0][0] + p.y() * m[0][1] + p.z() * m[0][2]+m[0][3];
            float y = p.x() * m[1][0] + p.y() * m[1][1] + p.z() * m[1][2]+m[1][3];
            float z = p.x() * m[2][0] + p.y() * m[2][1] + p.z() * m[2][2]+m[2][3];
            float w = p.x() * m[3][0] + p.y() * m[3][1] + p.z() * m[3][2]+m[3][3];
            return new Point3F(x/w, y/w, z/w);
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }



    public Point4F multiply(Point4F p) {
        if (column == 4) {
            float x = p.x() * m[0][0] + p.y() * m[0][1] + p.z() * m[0][2]+p.w()*m[0][3];
            float y = p.x() * m[1][0] + p.y() * m[1][1] + p.z() * m[1][2]+p.w()*m[1][3];
            float z = p.x() * m[2][0] + p.y() * m[2][1] + p.z() * m[2][2]+p.w()*m[2][3];
            float w = p.x() * m[3][0] + p.y() * m[3][1] + p.z() * m[3][2]+p.w()*m[3][3];
            return new Point4F(x, y, z, w);
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }





    public Vector4F multiply(Vector4F v) {
        if (column == 4) {
            float x = v.x() * m[0][0] + v.y() * m[0][1] + v.z() * m[0][2]+v.w()*m[0][3];
            float y = v.x() * m[1][0] + v.y() * m[1][1] + v.z() * m[1][2]+v.w()*m[1][3];
            float z = v.x() * m[2][0] + v.y() * m[2][1] + v.z() * m[2][2]+v.w()*m[2][3];
            float w = v.x() * m[3][0] + v.y() * m[3][1] + v.z() * m[3][2]+v.w()*m[3][3];
            return new Vector4F(x, y, z, w);
        }
        else {
            throw new IllegalArgumentException("Умножение матриц с несовпадающим количеством столбцов-строк.");
        }
    }






    public static MatrixF identity(int size) {
        MatrixF m = new MatrixF(size);
        for (int i = 0; i < size; i++) {
            m.set(i, i, 1.0f);
        }
        return m;
    }


    public static MatrixF zeroMatrix(int size){
        MatrixF m=new MatrixF(size);
        for(int i=0; i<size;i++){
            for (int j=0; j<size; j++){
                m.set(i, j, 0.0f);
            }
        }
        return m;
    }


    public static MatrixF zeroMatrix(int row, int column){
        MatrixF m=new MatrixF(row, column);
        for(int i=0; i<row;i++){
            for (int j=0; j<column; j++){
                m.set(i, j, 0.0f);
            }
        }
        return m;
    }


    public static MatrixF rotationMatrix(float alpha, float beta, float gamma) {
        float sinA = sin(alpha);
        float sinB =  sin(beta);
        float sinG = sin(gamma);
        float cosA =   cos(alpha);
        float cosB = cos(beta);
        float cosG = cos(gamma);
        MatrixF m = new MatrixF(new float[][]
            {{cosB*cosG,                -sinG*cosB,                 sinB,      0},
             {sinA*sinB*cosG+sinG*cosA, -sinA*sinB*sinG+cosA*cosG, -sinA*cosB, 0},
             {sinA*sinG-sinB*cosA*cosG,  sinA*cosG+sinB*sinG*cosA,  cosA*cosB, 0},
             {0,                         0,                         0,         1}});
        return m;
    }



    public static MatrixF rotationYMatrix(float phi) {
        float sinF = sin(phi);
        float cosF = cos(phi);
        MatrixF m=new MatrixF(new float[][]
                {{1,0,   0,    0},
                 {0,cosF,-sinF,0},
                 {0,sinF,cosF, 0},
                 {0,0,   0,    1}});
        return m;
    }


    public static MatrixF rotationXMatrix(float phi) {
        float sinF = sin(phi);
        float cosF = cos(phi);
        MatrixF m=new MatrixF(new float[][]
                {{cosF, 0,sinF,0},
                 {0,    1,0,   0},
                 {-sinF,0,cosF,0},
                 {0,    0,0,   1}});
        return m;
    }


    public static MatrixF rotationZMatrix(float phi) {
        float sinF = sin(phi);
        float cosF = cos(phi);
        MatrixF m=new MatrixF(new float[][]
                {{cosF,-sinF,0,0},
                 {sinF,cosF, 0,0},
                 {0,   0,    1,0},
                 {0,   0,    0,1}});
        return m;
    }


    public static MatrixF translationMatrix(float dx, float dy, float dz){
        MatrixF m=new MatrixF(new float[][]
                {{1, 0, 0, dx},
                 {0, 1, 0, dy},
                 {0, 0, 1, dz},
                 {0, 0, 0, 1}});
        return m;
    }
    public static MatrixF invertXMatrix(){
        MatrixF m=new MatrixF(new float[][]
                {{-1, 0, 0, 0},
                 { 0, 1, 0, 0},
                 { 0, 0, 1, 0},
                 { 0, 0, 0, 1}});
        return m;
    }

    public static MatrixF invertYMatrix(){
        MatrixF m=new MatrixF(new float[][]
                {{1,  0, 0, 0},
                 {0, -1, 0, 0},
                 {0,  0, 1, 0},
                 {0,  0, 0, 1}});
        return m;
    }

    public static MatrixF invertZMatrix(){
        MatrixF m=new MatrixF(new float[][]
                {{1, 0,  0, 0},
                 {0, 1,  0, 0},
                 {0, 0, -1, 0},
                 {0, 0,  0, 1}});
        return m;
    }


    public static MatrixF scalingMatrix(float sx, float sy, float sz){
        MatrixF m=new MatrixF(new float[][]
                {{sx,0, 0, 0},
                 {0, sy,0, 0},
                 {0, 0, sz,0},
                 {0, 0, 0, 1}});
        return m;
    }



    public static MatrixF perspectiveMatrix(float FOV, float aspect, float near, float far){
        float f=(1/tan(FOV/2));
        MatrixF m=new MatrixF(new float[][]
                {{f/(aspect),0, 0,                     0                      },
                 {0,           f, 0,                     0                      },
                 {0,           0, (near+far)/(near-far), 2*(far*near)/(near-far)},
                 {0,           0, 1,                    0                      }});
        return m;

    }

    public static MatrixF viewPort(int width, int height, float near, float far){
        MatrixF m=new MatrixF(new float[][]
                {{width/2, 0,  0, width/2},
                        {0, height/2,  0, height/2},
                        {0, 0, (far-near)/2, (far+near)/2},
                        {0, 0,  0, 1}});
        return m;
    }


    public static MatrixF lookAtMatrix(Point3F center, Point3F target){
        Vector3F up= Vector3F.Y;
        Vector3F direction=new Vector3F(center, target);
        Vector3F zAxis=direction.normalize();
        Vector3F xAxis=up.cross(zAxis).normalize();
        Vector3F yAxis=zAxis.cross(xAxis).normalize();
        MatrixF m1=translationMatrix(-center.x(), -center.y(), -center.z());
        MatrixF m2=new MatrixF(new float[][]
                {{xAxis.x(), xAxis.y(), xAxis.z(), 0},
                 {yAxis.x(), yAxis.y(), yAxis.z(), 0},
                 {zAxis.x(), zAxis.y(), zAxis.z(), 0},
                 {0,         0,         0,         1}});
        MatrixF m=m2.multiply(m1);
        return m;
    }


    public static MatrixF lookAtMatrix(Camera camera){
        Point3F center=camera.center();
        Vector3F up= Vector3F.Y;
        Vector3F direction=camera.direction();
        Vector3F zAxis=direction.normalize();
        Vector3F xAxis=zAxis.cross(up).normalize();
        Vector3F yAxis=xAxis.cross(zAxis).normalize();
        MatrixF m1=translationMatrix(-center.x(), -center.y(), -center.z());
        MatrixF m2=new MatrixF(new float[][]
                {{xAxis.x(), xAxis.y(), xAxis.z(), 0},
                 {yAxis.x(), yAxis.y(), yAxis.z(), 0},
                 {zAxis.x(), zAxis.y(), zAxis.z(), 0},
                 {0,         0,         0,         1}});
        MatrixF m=m2.multiply(m1);
        return m;
    }



    public static MatrixF build(MatrixF... matrices){
        int size=matrices[0].row();
        MatrixF m= MatrixF.identity(size);
        for(int i=0; i<matrices.length; i++){
            m=m.multiply(matrices[i]);
        }
        return m;
    }

    public MatrixF gaussMethod() {
        float a;
        MatrixF m = new MatrixF(this.m);
        for (int k = 0; k < m.row(); k++) {
            for (int i = k + 1; i < m.row(); i++) {
                a = m.get(i, k) / m.get(k, k);
                for (int j = 0; j < m.column(); j++) {
                    m.set(i, j, m.get(i, j) - a * m.get(k, j));
                }
            }
        }
        for (int k = m.row()-1; k >=0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                a = m.get(i, k) / m.get(k, k);
                for (int j = m.column()-1; j >= 0; j--) {
                    m.set(i, j, m.get(i, j) - a * m.get(k, j));

                }
            }
        }
        return m;
    }
    public float determinant() {
        if (row == column) {
            int size = row;
            float a;
            float det = 1.0f;
            MatrixF m = new MatrixF(this.m);
            for (int k = 0; k < size; k++) {
                for (int i = k + 1; i < size; i++) {
                    a = m.get(i, k) / m.get(k, k);
                    for (int j = 0; j < size; j++) {
                        m.set(i, j, m.get(i, j) - a * m.get(k, j));
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                det = det * m.get(i, i);
            }
            return det;
        }
        else {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
    }
    public MatrixF invert() {
        if (row == column) {
            int size = row;
            float a;
            MatrixF m = new MatrixF(this.m);
            MatrixF E= identity(size);
            for (int k = 0; k < size; k++) {
                for (int i = k + 1; i < size; i++) {
                    a = m.get(i, k) / m.get(k, k);
                    for (int j = 0; j < size; j++) {
                        E.set(i, j, E.get(i, j) - a * E.get(k, j));
                        m.set(i, j, m.get(i, j) - a * m.get(k, j));
                    }
                }
            }
            for (int k = size-1; k >= 0; k--) {
                for (int i = k - 1; i >= 0; i--) {
                    a = m.get(i, k) / m.get(k, k);
                    for (int j = size-1; j >= 0; j--) {
                        E.set(i, j, E.get(i, j) - a * E.get(k, j));
                        m.set(i, j, m.get(i, j) - a * m.get(k, j));
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                a = m.get(i, i);
                for (int j = 0; j < size; j++) {
                    E.set(i, j, E.get(i, j) / a);
                }
            }
            return E;
        }
        else {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
    }
}
