import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
	static int divisor = 1000000;
	static int p = (divisor/10) * 15;

	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());

		arr = new int[p+1];
		arr[0] = 0;
		arr[1] = 1;

		for(int i=2; i<p; i++){
			arr[i] = (arr[i-1] + arr[i-2] ) % divisor;
		}
		int idx = (int)(n%p);
		int result = arr[idx];
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}

