/**
*
* Find the longest length of two string for their common string
* At most O(n^2) time and O(1) space
* check diagonal (left top - right down), do not using extra constance space
*/
import java.util.*;
public class LongestCommon{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String t1 = scan.next();
		String t2 = scan.next();
		System.out.println(findCommon(t1,t2));
	}
	private static int findCommon(String s1,String s2){
		if(s1.length() == 0 || s2.length() == 0) return 0;
		int max = 0;
		int i = s1.length() - 1, j = 0;
		while(j < s2.length()){
			max = Math.max(max,support(s1,s2,i,j));
			if(i > 0) i--;
			else j++;
		}
		return max;
	}
	private static int support(String s1,String s2,int i,int j){
		int value = 0, max = 0;
		while(i < s1.length() && j < s2.length()){
			if(s1.charAt(i ++) == s2.charAt(j ++)){
				value ++;
				max = Math.max(value,max);
			}
			else value = 0;
		}
		return max;
	}
}