import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


class Pair{
	int l;
	int r;

	Pair(int l, int r){
		this.l = l;
		this.r = r;
	}
}

class Main {
	static int[] arr;
	static int n;
	static int[] lis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new int[n];
		lis = new int[n];	// LIS를 이루는 수를 저장할 배열
		Pair[] trace = new Pair[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// LIS 찾기
		int idx = 0;

		lis[idx] = arr[0];
		trace[0] = new Pair(0, arr[0]);
		for(int i=1; i<n; i++){
			if(lis[idx] < arr[i]){
				lis[++idx] = arr[i];
				trace[i] = new Pair(idx, arr[i]);
			}
			else{
				int lower_bound = binarySearch(0, idx, arr[i]);
				lis[lower_bound-1] = arr[i];

				trace[i] = new Pair(lower_bound-1, arr[i]);
			}
		}		
		System.out.println(idx+1);	// LIS 길이 출력

		Stack<Integer> stack = new Stack<>();
		// LIS 출력
		for(int i=n-1; i>=0; i--){
			if(trace[i].l == idx){
				stack.push(trace[i].r);
				idx--;
			}
		}

		while(!stack.isEmpty()){
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}

	private static int binarySearch(int left, int right, int v){
		while(left < right){
			int mid = (left + right) / 2;
			if(lis[mid] >= v)
				right = mid;
			else
				left = mid +1;
		}

		return right + 1;
	}
}