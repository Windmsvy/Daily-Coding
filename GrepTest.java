import java.io.*;

public class GrepTest{
	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Grep.out")));
		for(int i = 1; i <= 1000; i++){
			int number = (int)(Math.random() * 1000);
			out.println(number + "" + trans((int)(Math.random() * 52)) + trans((int)(Math.random() * 52)));
		}
		out.close();
	}
	private static char trans(int i){
		if(i < 26) return (char)('a' + i);
		return (char)('A' + i - 26);
	}
}