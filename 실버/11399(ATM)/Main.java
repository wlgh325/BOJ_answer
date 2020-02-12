import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");

		int[] arr = new int[n];

		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(temp[i]);

		Arrays.sort(arr);

		int sum = 0;
		int num = n;
		for(int i=0; i<n; i++, num--)
			sum += arr[i]*num;
		bw.write("" + sum);

		bw.flush();
		br.close();
		bw.close();
	}
}