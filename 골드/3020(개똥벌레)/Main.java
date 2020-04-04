import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	static int N, H;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		H = Integer.parseInt(temp[1]);
		
		// 짝수 번째 index는 아래, 홀수는 위에 달려있음
		int[] bottom = new int[H+2];
		int[] top = new int[H+2];
		
		for(int i=0; i<N; i++){
			if(i % 2 == 0)
				bottom[Integer.parseInt(br.readLine())]++ ;
			else
				top[Integer.parseInt(br.readLine())]++;
		}
		for(int i=H; i>=2; --i){
			bottom[i-1] += bottom[i];
			top[i-1] += top[i];
		}

		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for(int i=1; i<=H; i++){
			int sum = bottom[i] + top[H-i+1];
			if(min > sum){
				min = sum;
				cnt = 1;
			}
			else if(min == sum)
				cnt++;
		}

		System.out.println(min);
		System.out.println(cnt);
	}
}