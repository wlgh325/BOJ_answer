import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		if(n==1)
			System.out.println(1);
		else {
			int i=1;
			int sum = 0;
			while(true) {
				sum = (int)Math.pow(2, i) - 1;
				i++;
				if(sum >= n-1)
					break;
			}
			int t = (int)Math.pow(2, i-2) - 1;
			
			System.out.println(2*(n-1-t));
		}
		
	}
}