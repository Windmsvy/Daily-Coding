import java.io.*;
public class HashCompute{
	public static void main(String[] args) throws IOException{
		File file = new File("hashResult.csv");
		file.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		for(int i = 0; i <= 1000000; i++){
			String part = i + "," +hash(i) + "\r";
			out.write(part);
			out.flush();
		}	
		out.close();
	}
	private static int hash(int h) {  
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);  
    }
}