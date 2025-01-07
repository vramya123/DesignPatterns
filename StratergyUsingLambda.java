/*

Ref :- 
https://www.youtube.com/watch?v=yTuwi--LFsM
https://www.geeksforgeeks.org/java-8-predicate-with-examples/

Stratergy Pattern In java using Lambda

Stratergy are often single method or function, so functional interface and lambda fit perfectly here.


A Functional Interface is an Interface which allows only one Abstract method within the Interface scope. 
There are some predefined functional interface in Java like Predicate, consumer, supplier etc. 
The return type of a Lambda function (introduced in JDK 1.8) is a also functional interface.

The Functional Interface PREDICATE is defined in the java.util.function package. 
It improves manageability of code, helps in unit-testing them separately.

*/


import java.io.*;
import java.util.*;
import java.util.function.Predicate;


public class StratergyUsingLambda{
    
    
    public static int  getTotatlNumbers(List<Integer> numbers, Predicate<Integer> selector){
        int totalNum = 0;
        
        //Legacy Code
        for(int num : numbers){
            if(selector.test(num)){
                totalNum+= num;
            }
        }
        
        //functional Programming
        
        totalNum = numbers.stream()
        .filter(selector)
        .mapToInt(e-> e)
        .sum();
        
        return totalNum;
    }
    
    public static boolean isOdd(int n){
        
        return (n%2 != 0);
    }

    public static void main(String[] args){
        
        var num = List.of(23, 56, 78, 12, 34,33, 77,81);
        
        System.out.println("The sum of total numbers is :"+getTotatlNumbers(num, ignore->true));
        
        
        System.out.println("The sum of total even numbers is :"+getTotatlNumbers(num, n -> n %2 == 0));
        
        
        System.out.println("The sum off total odd numbers is :"+getTotatlNumbers(num, n -> n %2 != 0));
        
        System.out.println("The sum off total odd numbers using inbuilt method is :"+getTotatlNumbers(num, StratergyUsingLambda::isOdd));
    }
}
