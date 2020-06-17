import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String a = br.readLine();
			boolean flag = true;
			if(a.equals("0")) {
				break;
			}
			int len = a.length();
			for(int i=0, len2=len/2; i<len2; i++) {
				if(a.charAt(i) != a.charAt(len-1-i)){
					flag = false;
					break;
				}
			}
			
			if(flag)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}