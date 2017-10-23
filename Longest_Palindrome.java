/*
*   @ author : Windmsvy
*   @ Problem : Longest Palindrome Problem
*   @ Description : Find the longest Palindrome substring in origin string
*   @ Source: LeetCode 4
*   @ Algorithm : Manacher's Algorithm (O(n))
*
 */
import java.util.*;
import java.io.*;
class Longest_Palindrome{
    public static void main(String[] args) throws IOException{
    	BufferedReader f = new BufferedReader(new FileReader("palindrome.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palindrome.out")));
        String sentence = f.readLine();
        while(sentence != null){
        	out.println(sentence);
    		out.println("Longest Palindrome : " + longestPal(sentence));
    		sentence = f.readLine();
    	}
    	out.close();
    }
    private static String longestPal(String s){
        if(s.length() <= 1) return s;
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length() - 1; i++){
        	str.append(s.charAt(i)).append('*');
        }
        str.append(s.charAt(s.length() - 1));
        int [] dp = new int [str.length()];
        int mid = 0, max = 0, maxloc = 0;
        for(int i = 0; i < dp.length; i++){
        	if(i >= mid + dp[mid]){
        		mid = i;
        	}
        	else{
        		dp[i] = Math.min(mid + dp[mid] - i, dp[2 * mid - i]);
        	}
        	while(i + dp[i] < str.length() - 1 && i - dp[i] > 0 && str.charAt(i - dp[i] - 1) == str.charAt(i + dp[i] + 1)){
        		dp[i] ++;
        	}
        	if(i + dp[i] > mid + dp[mid]){
        		mid = i;
        	}
        	if(dp[i] > max){
        		max = dp[i];
        		maxloc = i;
        	}
        }
        return s.substring((maxloc - max + 1)/2,(maxloc + max + 1)/2);
    }
}