public class PlusWithBit{
	public static void main(String[] args) {
		for(int t = 1; t <= 100; t++){
			int a = (int)(Math.random() * 1000000);
			int b = (int)(Math.random() * 1000000);
			System.out.println("Test " + t + " Result " + (plus(a,b) == (a+b)));
		}
	}
	private static int plus(int a,int b){
		while(b != 0){
			int temp = (a & b);
			a = (a ^ b);
			b = (temp << 1);
		}
		return a;
	}
}