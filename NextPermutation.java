/*
 *	@ Oct 25,2017
 *	@ Author : windmsy
 *	@ Next Permutation : Leetcode 31
 *	@ Algorithm :
 *		Step 1 : check from the end to the beginning, until A[i - 1] < A[i], store location (i - 1)
 *		Step 2 : find the first number larger than A[i - 1] (from end to start), swap them, then reverse the A[i to n - 1]
 *	@ Time Complextity : O(n)
 *	@ Space Complextity : O(1)
 *
 *
 */


import java.util.*;
class NextPermutation{
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 50; i++){
			list.add(i + 1);
		}
		for(int i = 1; i <= 50; i++){
			System.out.println("This is No. " + i + " Test!");
			Collections.shuffle(list);
			nextPermutation(list);
			System.out.println("-----------------------------------");
		}
	}
	private static void nextPermutation(List<Integer> nums) {
		printArray(nums);
        if(nums != null && nums.size() > 1){
            int i = nums.size() - 2;
            while(i >= 0 && nums.get(i) >= nums.get(i + 1)){
                i --;
            }
            if(i < 0){
                reverse(nums,0,nums.size() - 1);
            }
            else{
                int t = nums.size() - 1;
                while(nums.get(t) <= nums.get(i)){
                    t--;
                }
                int tmp = nums.get(i);
                nums.set(i,nums.get(t));
                nums.set(t,tmp);
                reverse(nums,i + 1,nums.size() - 1);
            }
        }
        printArray(nums);
    }
    private static void reverse(List<Integer> nums,int start,int end){
        while(start < end){
            int tmp = nums.get(start);
            nums.set(start++,nums.get(end));
            nums.set(end--,tmp);
        }
    }
    private static void printArray(List<Integer> nums){
    	StringBuilder str = new StringBuilder();
    	for(int i : nums){
    		str.append(i).append(',');
    	}
    	System.out.println(str);
    }
}