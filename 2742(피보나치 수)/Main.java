import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
		
		int sum = fibo(n-1);
		
		bw.write(String.valueOf(sum));
		bw.flush();

		br.close();
		bw.close();
	}

	public static int fibo(int n){
		if(n <= 1)
			return 1;
        if(arr[n] != 0){
            return arr[n];
        }else{
            arr[n] = fibo(n-1)+ fibo(n-2);
        }
		return arr[n];
	}
	
}

