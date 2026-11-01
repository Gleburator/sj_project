package extension;

import java.util.Arrays;

public final class Printing {
    private Printing(){}
    public static void print(double[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    public static <T>void print(T[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    public static void print() {
        System.out.println();
    }
    public static <T>void print(T[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }
    public static<T> void print(T[][][] arr) {
        System.out.println(Arrays.deepToString(arr));

    }
    public static void printObject(Object obj) {
        System.out.println(obj.toString());
    }
}


