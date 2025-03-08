package in.springsecurity.controller.revision;

import java.util.HashMap;

public class StringCharOccurance {

    public static void main(String[] args) {
        String name = "rajeev";

        char[] arrayOfName = name.toCharArray();

        HashMap<Character , Integer> hmap = new HashMap<>();

       for( char i:arrayOfName){
           hmap.put(i , hmap.getOrDefault(i,0)+1);
       }

        System.out.println("The occurrences are as follows :" + hmap);

    }
}
