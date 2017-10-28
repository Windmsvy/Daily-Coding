import java.util.*;
public class BigNumberMultiply{
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        String s1 = scan.next();
        String s2 = scan.next();
        int len1 = s1.length();
        int len2 = s2.length();
        int [] count = new int [len1 + len2 - 1];
        for(int i = 0; i < len1; i++){
            for(int j = 0; j < len2; j++){
                count[i + j] += (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
            }
        }
        StringBuilder str = new StringBuilder();
        int pre = 0;
        for(int t = count.length - 1; t >= 0; t --){
            int tmp = pre + count[t];
            pre = tmp / 10;
            str.insert(0,tmp % 10);
        }
        if(pre > 0) str.insert(0,pre);
        System.out.println(str);
    }
}