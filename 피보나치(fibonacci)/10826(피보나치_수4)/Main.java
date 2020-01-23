import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

class Main{	
	static BigInteger arr[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());

		arr = new BigInteger[n+2];
		arr[0] = BigInteger.ZERO;
		arr[1] = BigInteger.ONE;
		
		for(int i=2; i<=n; i++) {
			arr[i] = arr[i-2].add(arr[i-1]);
		}
		
		bw.write(String.valueOf(arr[n]));
		
		bw.flush();
		br.close();
		bw.close();
	}
}