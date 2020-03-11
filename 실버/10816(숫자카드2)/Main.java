import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// binary search를 위한 오름차순 정렬
		Arrays.sort(arr);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<m; i++) {
			int t = Integer.parseInt(st.nextToken());
			System.out.print((UpperBinarySearch(t) - LowerBinarySearch(t)) + " ");
		}
	}
	
	// target 이상의 수가 처음 나오는 위치를 찾는다.
	private static int LowerBinarySearch(int target) {
		int left = 0;
		int right = arr.length -1;
		int mid;
		
		// lower bound
		while(left < right) {
			mid = (left + right) / 2;
			if(arr[mid] >= target) right = mid;
			else
				left = mid + 1;
		}

		return right;
	}

	// target을 초과한 수가 처음 나오는 위치를 찾는다.
	private static int UpperBinarySearch(int target){
		int left = 0;
		int right = arr.length - 1;
		int mid;

		// upper bound
		while(left < right){
			mid = (left + right) / 2;
			if(arr[mid] > target) right = mid;
			else
				left = mid + 1;
		}

		// 찾으려는 수가 맨 끝쪽에 있는 경우 예외처리
		if(arr[right] == target)
			return right+1;
		return right;
	}
}