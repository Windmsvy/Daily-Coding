import java.util.*;
public class Bracket{
	public static void main(String[] args) {
		List<String> ans = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		ans = find(size);
		for(String p : ans){
			System.out.print(p + " ");
		}
		System.out.println();
	}
	private static List<String> find(int n){
		List<String> list = new ArrayList<>();
		StringBuilder str = new StringBuilder();
		support(list,str,0,0,n);
		return list;
	}
	private static void support(List<String> list, StringBuilder str,int left,int right,int total){
		if(right == total){
			list.add(new String(str.toString()));
			return;
		}
		if(left < total){
			str.append('(');
			support(list,str,left + 1,right,total);
			str.deleteCharAt(str.length() - 1);
		}
		if(right < left){
			str.append(')');
			support(list,str,left,right + 1,total);
			str.deleteCharAt(str.length() - 1);
		}
	}
}