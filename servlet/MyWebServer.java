import java.io.*;
import java.net.*;
public class MyWebServer{
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(9999);
		Socket s = ss.accept();

		System.out.println("Waiting for connection at 9999");
		OutputStream os = s.getOutputStream();
		BufferedReader br = new BufferedReader(new FileReader("sample.html"));
		String buffer = br.readLine();
		while(buffer != null){
			os.write(buffer.getBytes());
			buffer = br.readLine();
		} 
		br.close();
		os.close();
		s.close();
	}
}