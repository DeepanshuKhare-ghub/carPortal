package in.springsecurity.controller.revision;

import java.util.Arrays;

public class ArraySort {


    public static void main(String[] args) {
        // create an array

        // bubble sort to sort
        // the light elements comes up

        //
        int [] arr = {1 , 3  , 4 , 45 , 56 , 2};
        System.out.println("unSorted Array: " + Arrays.toString(arr));
        for( int j =0 ; j<arr.length-1; j++){
            for(int i = 0; i<arr.length-1;i++){
                if(arr[i] >=arr[i+1]){
                    int x = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = x;
                }
            }
        }


        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}
