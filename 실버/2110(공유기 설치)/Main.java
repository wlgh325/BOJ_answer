import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Print{
	int idx;
	int importance;

	Print(int idx, int importance){
		this.idx = idx;
		this.importance = importance;
	}
}

class Main {
	static int N, C;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		
		arr = new int[N];
		// 입력받기
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);	// 이분탐색을 위한 정렬

		System.out.println(binarySearch());
		br.close();
	}

	public static int binarySearch(){
		int left = 1;	// 최소 길이
		int right = arr[arr.length-1] - arr[0];	// 최대 길이(max - min)
		int ans = 0;	// 답
		// 공유기 간격을 바꿔가면서 해본다.
		while(left <= right){
			int mid = (left + right) / 2;	// 공유기 간격 mid 만큼 해보기
			int start = arr[0];	// 가장 최근 설치한 공유기 위치
			int cnt = 1;	// 설치 가능한 공유기의 개수(맨 왼쪽에는 무조건 설치하므로 초기값 1)
			int d = 0;
			for(int i=1, n=arr.length; i<n; i++){
				d = arr[i] - start;	// 간격
				if(mid <= d){
					// mid 간격으로 설치할 수 있으므로 설치한 공유기 개수 증가
					cnt++;
					start = arr[i];
				}
			}

			// C개 이상 설치 가능하다면 오른쪽 탐색
			// 더 작게 설치하면 당연히 C개 설치가 되기 때문
			if(cnt >= C){
				ans = ans < mid ? mid : ans;	// 설치가능 했던 공유기 간격 저장
				left = mid + 1;
			}
			else
				right = mid - 1;
		}
		return ans;
	}
}