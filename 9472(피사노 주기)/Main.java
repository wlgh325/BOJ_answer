import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum = Integer.parseInt(br.readLine());

		for(int i=0; i<testNum; i++){
			String[] temp = br.readLine().split(" ");
			int n = i+1;
			int m = Integer.parseInt(temp[1]);
			int result;

			// 주기 구하기
			if(n % 2 == 0){
				if(n % 5 == 0){
					result = 6 * n;
				}
				else{
					double temp2 = Math.log((double)m) / Math.log(2.0);
					double temp3 = Math.pow(2, temp2 - 1) * 3;
					bw.write(String.valueOf((int)temp3));
				}
			}
			else if( n % 5 == 0){
				double temp2 = Math.log((double)m) / Math.log(5.0);
				double temp3 = Math.pow(5, temp2) * 4;
				bw.write(String.valueOf((int)temp3));
			}
			else{
				double temp2 = log10(15* (temp2-2));
			}

			bw.write(String.valueOf(i+1) + " ");
			
		}
        
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

