package in.springsecurity.controller.revision;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ArrayLargest {

    public static void main(String[] args) {

        int [] arr = {1 , 34 , 4, 64, 3, 1030 , 432, 353,363,767};

        int max = arr[0];
//        for( int i = 0; i <arr.length; i++){
//
//        }

        int i =0;
        while(i<arr.length-1){
            if(arr[i]>max){
                max = arr[i];
            }
            i++;
        }

        System.out.println(max);
    }
}
