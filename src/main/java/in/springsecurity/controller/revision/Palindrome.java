package in.springsecurity.controller.revision;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the num");
        int num = sc.nextInt();
        char[] numbers = String.valueOf(num).toCharArray();


        boolean isPalindrome = true;
        for(int i = 0; i<numbers.length/2; i++){
            if(numbers[i] != numbers[numbers.length-1 -i]){
               isPalindrome = false;
               break;
            }
        }

        System.out.println(isPalindrome);
    }
}
