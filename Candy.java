import java.util.*;
public class Candy{
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] demand = new int [n];
        for(int i = 0; i < n; i++) demand[i] = scan.nextInt();
        int own = scan.nextInt();
        int [] candy = new int [own];
        for(int i = 0; i < own; i++) candy[i] = scan.nextInt();
        Arrays.sort(demand);
        Arrays.sort(candy);
        int i = 0, j = 0;
        int count = 0;
        while(i < n && j < own){
            if(demand[i] <= candy[j]){
                count++;
                i++;
            }
            j++;
        }
        System.out.println(count);
    }
}