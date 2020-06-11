import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int N;
	static int[] A, B, C;
	static int min, max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		A = new int[N];
		B = new int[N];
		C = new int[N];
		min = Integer.MAX_VALUE;
		max = 0;
		for(int i=0; i<N; i++){
			String[] temp = br.readLine().split(" ");
			A[i] = Integer.parseInt(temp[0]);
			C[i] = Integer.parseInt(temp[1]);
			B[i] = Integer.parseInt(temp[2]);
			min = min > A[i] ? A[i] : min;
			max = max < C[i] ? C[i] : max;
		}
		max ++;
		binarySearch();
		System.out.println(min);
		System.out.println(max);
	}
	
	public static void binarySearch() {
		long left = min;
		long right = max;
		
		while(left < right) {
			long mid = (left + right) / 2;
			
			// 누적 합이 짝수면 오른쪽 어딘가에 홀 수 누적합이 있을 것!
			long sum = getSum(mid);
			if(sum % 2 == 0) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		
		// 홀수인게 없으면 계속 오른쪽으로 가다가 초기 right 값에 도달한다.
		if(left == max)
			System.out.println("NOTHING");
		else {
			long num = getSum(left) - getSum(left - 1);
			System.out.println(left + " " + num);
		}
	}
	
	public static long getSum(long mid) {
		long sum = 0;
		for(int i=0; i<N; i++){
			if(mid >= A[i])
				sum += (Math.min(mid, C[i]) - A[i]) / B[i] + 1;
		}
		return sum;
	}
}