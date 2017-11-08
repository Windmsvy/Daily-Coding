public class QuickSort{
	public static void main(String[] args) {
		int [] test = new int [20];
		for(int i = 0; i < 20; i++) test[i] = (int)(Math.random() * 100);
		for(int t = 0; t < 5; t++){
			shuffle(test);
			sort(test,0,test.length - 1);
			System.out.print("Already sorted: ");
			for(int i : test){
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}
	public static void sort(int [] nums,int a,int b){
		if(a >= b) return;
		if(a + 1 == b){
			if(nums[a] > nums[b]) swap(nums,a,b);
		}
		int par = partition(nums,a,b);
		sort(nums,a,par - 1);
		sort(nums,par + 1,b);
	}
	public static int partition(int [] nums,int a,int b){
		int index = a;
		int label = nums[index];
		for(int i = a; i <= b; i++){
			if(nums[i] <= label){
				if(i != index){
					swap(nums,i,index);
				}
				index++;
			}
		}
		swap(nums,a,index - 1);
		return index - 1;
	}
	private static void swap(int [] nums,int a, int b){
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}
	private static void shuffle(int [] nums){
		int len = nums.length;
		for(int i = nums.length - 1; i > 0; i--){
			int choose = (int)(Math.random() * len);
			swap(nums,i,choose);
			len --;
		}
		System.out.print("Ready for sort: ");
		for(int i : nums){
			System.out.print(i + " ");
		}
		System.out.println("");
	}
}