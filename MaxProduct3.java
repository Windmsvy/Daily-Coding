import java.util.*;
public class MaxProduct3{
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int first = scan.nextInt();
        int second = scan.nextInt();
        long max1 = Math.max(first,second);
        long min1 = Math.min(first,second);
        long max2 = first * second;
        long min2 = max2;
        long max = 0;
       while(scan.hasNext()){
            long t = scan.nextLong();
            max = Math.max(max,Math.max(max2 * t,min2 * t));
            max2 = Math.max(max2,Math.max(max1 * t,min1 * t));
            min2 = Math.min(min2,Math.min(max1 * t,min1 * t));
            max1 = Math.max(max1,t);
            min1 = Math.min(min1,t);
        }
        System.out.println(max);
    }
}