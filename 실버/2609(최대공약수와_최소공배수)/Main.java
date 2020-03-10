import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int gcd = 0;	// great common divisor		
		
		for(int i=1, end=Math.min(a, b); i<=end; i++) {
			if(a % i == 0 && b % i == 0)
				gcd = gcd < i ? i : gcd;
		}
		
		int lcm = (a*b) /gcd;	// least common multiple		
		
		System.out.println(gcd);
		System.out.println(lcm);
		br.close();
	}
}