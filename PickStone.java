/*
 *
 *	@ author: windmsvy
 *	@ time: Oct 23,2017
 *	@ Main idea:
 *		PickStone(Wythoff Game), you and your friends pick stone from two heap
 *		Each of you can choose to pick (0,k) or (k,0) or (k,k).
 *		People take the last stone will be the winner.
 *		You pick first, and find out whether you can win in this game
 *		See in wikipedia: https://en.wikipedia.org/wiki/Wythoff%27s_game
 *		
 *		Solution:
 *			1) Best solution, using Mathmatics method (function origin), O(1)
 *			2) Naive solution, check from the begining, O(mn) two dimension DP
 *				if (i,j) is the condition that you must lose, (i + k, j) and (i,j + k) and (i + k, j + k) you can win
 *				Just delete those points you can win
 *			3) Better dp solution, notice that ith fail pair with difference i and one number only occur once
 *				(1,2)(3,5)(4,7)(6,10)...etc， so using hashset to store taken number, and using diff to calculate fail case
 *				using O(n) space and O(m) time
 */
import java.util.*;
public class PickStone{
	public static void main(String[] args) {
		for(int i = 0; i < 50; i++){
			int m = (int)(Math.random() * 10000);
			int n = (int)(Math.random() * 10000);
			boolean result = origin(m,n);
			//boolean test = firstCheck(m,n);
			boolean test = secondCheck(m,n);
			System.out.println("m is " + m + " ," + "n is " + n + " ,run result:" + result + " ,status:" + (result == test));
		} 
	}
	private static boolean firstCheck(int m,int n){
		swap(m,n);
		boolean [][] dp = new boolean [m + 1][n + 1];
		for(int i = 0; i <= m; i++){
			for(int j = 0; j <= n; j++){
				if(!dp[i][j]){
					for(int k = 1; i + k <= m || j + k <= n; k++){
						if(i + k <= m){
							dp[i + k][j] = true;
						}
						if(j + k <= n){
							dp[i][j + k] = true;
						}
						if(i + k <= m && j + k <= n){
							dp[i + k][j + k] = true;
						}
					}
				}
			}
		}
		return dp[m][n];
	}
	private static boolean secondCheck(int m,int n){
		if(m == 0 && n == 0) return false;
		swap(m,n);
		HashSet<Integer> taken = new HashSet<Integer>();
		int diff = 1;
		for(int i = 1; i <= m; i++){
			if(!taken.contains(i)){
				int next = i + diff;
				if(i + diff == m || i + diff > n) return true;
				if(i == m && i + diff == n) return false;
				taken.add(i);
				taken.add(i + diff);
				diff++;
			}
		}
		return true;
	}
	private static boolean origin(int m,int n){
		swap(m,n);
		double eqa = (1 + Math.sqrt(5)) / 2.0;
		int diff = n - m;
		return m != (int)(diff * eqa);
	}
	private static void swap(int m,int n){
		if(m > n){
			int tmp = m;
			m = n;
			n = tmp;
		}
	}
}