//Обобщения на Java лучше не делать для числовых типов

package linearalgebra;
public abstract class Matrix{
    protected final int row;
    protected final int column;
    public Matrix(int row, int column){
        this.row=row;
        this.column=column;
    }
    public Matrix(int size){
        this.row=size;
        this.column=size;
    }
    public int row(){
        return row;
    }
    public int column(){
        return column;
    }
}