import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.List;

class Main{
	static long sum = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Integer> list = new ArrayList<>();
		
		// 입력받기
		for(int i=0; i<n; i++){
			int t = Integer.parseInt(st.nextToken());
			list.add(t);
		}

		List<Integer> sorted = divide(list);
		System.out.println(sum);
		br.close();
	}

	public static List<Integer> divide(List<Integer> list){
		if(list.size() > 1){
			return mergeSort(
				divide(list.subList(0, list.size()/2)),
				divide(list.subList(list.size()/2, list.size()))
			);
		}
		else{
			return list;
		}
	}

	public static List<Integer> mergeSort(List<Integer> left, List<Integer> right){
		List<Integer> result = new ArrayList<>();
		int rightIdx = 0;
		
		// 왼쪽 배열 순회
		for(int leftIdx=0; leftIdx<left.size(); leftIdx++){
			// right를 끝까지 탐색했나?
			// right배열의 값이 left의 값보다 작은지
			while(right.size() > rightIdx && left.get(leftIdx) > right.get(rightIdx)){
				sum += left.size() - leftIdx;
				// 작은걸 결과에 넣기
				result.add(right.get(rightIdx));
				rightIdx++;
			}
			// left보다 작은 것들 다 넣은후 뒤에 left 넣기
			result.add(left.get(leftIdx));
		}
		// 오른쪽 배열 남은 숫자 넣기
		for(int i=rightIdx; i<right.size(); i++)
			result.add(right.get(i));
		return result;
	}
}