import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int[] arr, sorted;
	static long sum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		sorted = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 선수들 실력 입력받기
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		sum = 0;
		// 오름차순 정렬
		merge(0, N-1);
		System.out.println(sum);
	}

	private static void merge(int left, int right){
		if(left >= right){
			return;
		}
		
		int mid = (left + right + 1) / 2;
		merge(left, mid-1);
		merge(mid, right);
		mergeSort(left, right);
	}

	private static void mergeSort(int left, int right){
		if(left >= right) return;
		int mid = (left + right + 1) / 2;
		int leftIdx = left;
		int rightIdx = mid;
		int cnt = left;

		// 왼쪽 : left ~ mid-1
		// 오른쪽 : mid ~ right
		while(leftIdx < mid && rightIdx <= right){
			// 작은 수를 sorted 배열에 먼저 넣는다.
			if(arr[leftIdx] < arr[rightIdx]){
				sorted[cnt++] = arr[leftIdx++];
			}
			else{
				// 오름 차순이므로 왼쪽의 수가 더 크다면 그 뒤의 숫자들 모두 오른쪽의 수 보다 큽니다.
				// 그 수만 큼 Inversion이 있습니다.
				// (4 5) / (1 2) 있다면 4와 1을 비교 할때 1이 더 작으므로 4뒤의 수 모두 1보다 큽니다.
				// 4-1, 5-1 inversion이 2개 있습니다. (mid- elftIdx = 2)
				sum += mid - leftIdx;
				sorted[cnt++] = arr[rightIdx++];
			}

		}
		// 남은거 넣기
		while(leftIdx < mid) sorted[cnt++] = arr[leftIdx++];
		while(rightIdx <= right) sorted[cnt++] = arr[rightIdx++];
		for(int i=left; i<=right; i++)
			arr[i] = sorted[i];
	}	
}