public class QuickSelect{
	public static void main(String[] args) {
		int [] test = {10,9,8,7,6,5,4,11,12,13,14,15,1,2,3,4};
		partition(test);
		for(int i : test){
			System.out.print(i + " ");
		}
		System.out.println("");
	}
	public static void partition(int [] nums){
		int index = 0;
		int label = nums[index];
		for(int i = 0; i < nums.length; i++){
			if(nums[i] <= label){
				if(i != index){
					swap(nums,i,index);
				}
				index++;
			}
		}
		swap(nums,0,index - 1);
	}
	private static void swap(int [] nums,int a, int b){
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
}