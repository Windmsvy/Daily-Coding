/**
*   LeetCode 719 Find K-th Smallest Pair Distance
*   Find the k-th absolute diff in an array
*   Link: https://leetcode.com/problems/find-k-th-smallest-pair-distance
*   Algorithm : 
*       Step 1) Sort array, calculate the min and max diff
*       Step 2) Using binary Search on min and max, count how many pair with diff small than mid
*       Time complexity O(nlog(maxdiff)) or O(nlogn), depends on data itself
*
*/
import java.util.*;
class KthSmallestPair {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[1] - nums[0], max = nums[nums.length - 1] - nums[0];
        for(int i = 2; i < nums.length; i++){
            min = Math.min(nums[i] - nums[i - 1], min);
        }
        while(min < max){
            int mid = min + (max - min)/2;
            if(countPair(nums,mid) < k) min = mid + 1;
            else max = mid;
        }
        return min;
    }
    private int countPair(int [] nums,int diff){
        int j = 0, count = 0;
        for(int i = 0; i < nums.length - 1; i++){
            while(j < nums.length && nums[j] - nums[i] <= diff) j++;
            count += j - i - 1;
        }
        return count;
    }
}