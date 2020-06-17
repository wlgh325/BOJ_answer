import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {
	static boolean[] visited;
	static int[] arr;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[9];
		for(int i=0; i<9; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		visited = new boolean[9];
		list = new ArrayList<>();
		
		// 조합 구하기(9C7)
		comb(0, 7);
	}
	
	public static void comb(int start, int r) {
		if(r==0) {
			// 키의 합이 100인지 확인
			int sum = 0;
			for(int i=0; i<9; i++) {
				if(visited[i]) {
					sum += arr[i];
				}
			}
			
			// 키의 합이 100이 맞으면 일곱 난쟁이 찾음
			if(sum == 100) {
				// 일곱 난쟁이 list에 모두 넣기
				for(int i=0; i<9; i++) {
					if(visited[i])
						list.add(arr[i]);
				}
				Collections.sort(list);	// 정렬

				// 출력
				for(Integer a: list)
					System.out.println(a);
				System.exit(0);	// 종료
			}
			return;
		}
		
		for(int i=start; i<9; i++) {
			visited[i] = true;
			comb(i+1, r-1);
			visited[i] = false;
		}
	}
}