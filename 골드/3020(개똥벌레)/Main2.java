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

		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=H; i++)
			list.add(bottom[i] + top[H-i+1]);
		
		Collections.sort(list);
		int min = list.get(0);
		int lower = lowerBound(min, list);
		int upper = upperBound(min, list);

		System.out.println(min);
		System.out.println(upper - lower);
	}

	private static int lowerBound(int target, ArrayList<Integer> list){
		int left = 0;
		int right = list.size()-1;
		int mid = 0;

		while(left < right){
			mid = (left + right) / 2;
			if(list.get(mid) >= target)
				right = mid;
			else
				left = mid+1;
		}

		return right;
	}

	private static int upperBound(int target, ArrayList<Integer> list){
		int left = 0;
		int right = list.size()-1;
		int mid = 0;

		while(left < right){
			mid = (left + right) / 2;
			if(list.get(mid) > target)
				right = mid;
			else
				left = mid+1;
		}

		if(list.get(right) == target)
			return right+1;
		return right;
	}
}