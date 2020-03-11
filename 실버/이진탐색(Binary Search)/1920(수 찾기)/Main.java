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
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			int result = BinarySearch(Integer.parseInt(st.nextToken()));
			if(result != -1)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
	
	private static int BinarySearch(int target) {
		int left = 0;
		int right = arr.length -1;
		int mid;
		
		while(left <= right) {
			mid = (left + right) / 2;
			// 중간 값 보다 왼쪽에 있음
			if(arr[mid] > target)
				right = mid-1;
			// 중간 값 보다 오른쪽에 있음
			else if(arr[mid] < target)
				left = mid+1;
			else
				return mid;
		}

		return -1;
	}
}