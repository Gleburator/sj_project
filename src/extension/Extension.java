package extension;

import static java.lang.Math.*;
public final class Extension {
    private Extension(){}
    public static void swap(double[] arr){
        double i=arr[0];
        arr[0]=arr[1];
        arr[1]=i;
    }
    public static void swap(double[] arr, int i, int j) {
        double a = arr[i];
        double b = arr[j];
        arr[i] = b;
        arr[j] = a;
    }
    public static <T> void swap(T[] arr, int i, int j) {
        T a = arr[i];
        T b = arr[j];
        arr[i] = b;
        arr[j] = a;
    }
    public static int count(char[] arr, char c) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                count++;
            }
        }
        return count;
    }
    public static int sum(char[] arr) {
        int sum=0;
        for(int i=0; i<arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
    public static <T> boolean equals(T[] arr1, T[] arr2) {
        boolean isEqual = true;
        if (arr1.length==arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].equals(arr2[i])) {
                    isEqual = false;
                    break;
                }
            }
        }
        else {
            isEqual=false;
        }
        return isEqual;
    }
}

