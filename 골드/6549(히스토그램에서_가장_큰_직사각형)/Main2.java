import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main2 {
	static int[] arr;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true){
			String s = br.readLine();
			if(s.equals("0"))
				break;
			st = new StringTokenizer(s, " ");
			n = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			for(int i=0; i<n; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			System.out.println(getMax(0, n-1));
		}
	}

	private static long getMax(int left, int right){
		if(left == right) return arr[left];

		int mid = (left + right) / 2;
		// 두 구간으로 나누어 계산, 양쪽 중에 큰 넓이를 반환 (구간이 1이면 높이== 넓이)
		long ret = Math.max(getMax(left, mid), getMax(mid+1, right));

		// 두 부분에 모두 걸치는 사각형을 구한다.
		int start = mid;
		int end = mid+1;
		// mid 기준 양쪽으로 너비 2만큼 겹치는 직사각형 먼저 고려
		long height = (long)Math.min(arr[start], arr[end]);
		ret = (long)Math.max(ret, height*2);
		
		//범위를 벗어나기 전까지 확장
		while(left < start || end < right){
			// 왼쪽 범위를 넘지 않았다면
			if(left < start && end < right){
				// 높이가 높은 쪽으로 확장
				if(arr[start -1] < arr[end+1])
					height = (long)Math.min(height, arr[++end]);
				else
					height = (long)Math.min(height, arr[--start]);
			}
			else if(left < start){
				// 오른쪽 범위를 넘은 경우 왼쪽으로만 확장
				height = (long)Math.min(height, arr[--start]);
			}
			else if(end < right){
				// 왼쪽 범위를 넘은 경우 오른쪽으로만 확장
				height = (long)Math.min(height, arr[++end]);
			}
			ret = Math.max(ret, height*(end-start+1));
		}
		return ret;
	}
}