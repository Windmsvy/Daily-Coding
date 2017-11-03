import java.util.*;
public class HeapSort{
	public static void main(String[] args) {
		int size = 200;
		int [] test = new int [size];
		for(int time = 1; time <= 20; time ++){
			for(int i = 0; i < size; i++){
				test[i] = (int)(Math.random() * 500) - 200;
			}
			heapsort(test);
			System.out.print("This is No. " + time + " test. Result is ");
			check(test);
		}
	}
	private static void check(int [] nums){
		boolean label = true;
		for(int i = 1; i < nums.length; i++){
			if(nums[i] < nums[i - 1]){
				label = false;
				break;
			}
		}
		System.out.println(label);
	}
	private static void heapsort(int [] nums){
		int size = nums.length;
		adjust(nums,size);
		while(size > 1){
			swap(nums,0, --size);
			adjust(nums,size,0);
		}
	}
	private static void swap(int [] nums, int a, int b){
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
	private static void adjust(int [] nums,int size){
		boolean alter = true;
		while(alter){
			alter =  false;
			for(int i = size - 1; i > 0; i--){
				if(i % 2 == 0){
					int left = i - 1;
					int former = left / 2;
					int maxlabel = nums[i] > nums[left] ? i : left;
					int max = Math.max(nums[i],nums[left]);
					if(max > nums[former]){
						alter = true;
						swap(nums,former,maxlabel);
					}
				}
				else if(i == size - 1 && i % 2 == 1){
					int former = i / 2;
					if(nums[former] < nums[i]){
						swap(nums,former,i);
						alter = true;
					}
				}
			}
		}
	}
	private static void adjust(int [] nums,int size,int i){
		boolean alter = true;
		while(i * 2 + 1 < size && alter){
			alter = false;
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if(right >= size){
				if(nums[i] < nums[left]){
					swap(nums,i,left);
					i = left;
					alter = true;
				}
			}
			else if(right < size){
				int maxlabel = nums[right] > nums[left] ? right : left;
				int max = Math.max(nums[left],nums[right]);
				if(nums[i] < max){
					swap(nums,i,maxlabel);
					i = maxlabel;
					alter = true;
				}
			}
		}
	}
}