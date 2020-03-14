import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class Num {
	int idx;
	int num;

	Num(int idx, int num){
		this.idx = idx;
		this.num = num;
	}
}

class Main {
	static int N;
	static Num[] arr, sorted;
	static int[] ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new Num[N];
		sorted = new Num[N];
		ans = new int[N];

		// 선수들 실력 입력받기
		for(int i=0; i<N; i++){
			int t = Integer.parseInt(br.readLine());
			arr[i] = new Num(i, t);
			ans[i] = i+1;
		}
		// 내림차순 정렬
		merge(0, N-1);
		for(int i=0; i<N; i++)
			System.out.println(ans[i]);
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

	// 자신 보다 앞에 있는 숫자 중 큰 숫자가 있다면 앞으로 땡길 수 있다.
	private static void mergeSort(int left, int right){
		if(left >= right) return;
		int mid = (left + right + 1) / 2;
		int leftIdx = left;
		int rightIdx = mid;
		int cnt = left;

		// 왼쪽 : left ~ mid-1
		// 오른쪽 : mid ~ right
		while(leftIdx < mid && rightIdx <= right){
			// 내림 차순이므로 왼쪽이 작다면 왼쪽을 sorted배열에 넣는다.
			if(arr[leftIdx].num > arr[rightIdx].num){
				sorted[cnt++] = arr[leftIdx++];
			}
			else{
				sorted[cnt++] = arr[rightIdx];
				// 오른쪽 수가 크다면 내림차순 이므로 그 뒤의 모든 수들 도 작다!
				// 전체 크기(mid) - 현재 leftIndex = 뒤에 있는 숫자 개수(leftIdx 포함)
				ans[arr[rightIdx++].idx] -= mid-leftIdx;
			}
		}
		// 남은거 넣기
		while(leftIdx < mid) sorted[cnt++] = arr[leftIdx++];
		while(rightIdx <= right) sorted[cnt++] = arr[rightIdx++];
		for(int i=left; i<=right; i++)
			arr[i] = sorted[i];
	}	
}