import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;

class Print{
	int idx;
	int importance;

	Print(int idx, int importance){
		this.idx = idx;
		this.importance = importance;
	}
}

class Main {
	static int N, find;
	static Queue<Print> q;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testNum = Integer.parseInt(br.readLine());
		for(int t=1; t<=testNum; t++){
			String[] temp = br.readLine().split(" ");
			N = Integer.parseInt(temp[0]);
			find = Integer.parseInt(temp[1]);

			// 문서의 중요도 queue와 list에 넣기
			q = new LinkedList<>();
			list = new ArrayList<>();

			temp = br.readLine().split(" ");
			for(int i=0; i<N; i++){
				int tmp = Integer.parseInt(temp[i]);
				q.offer(new Print(i,tmp));
				list.add(tmp);
			}

			// 중요도를 내림차순 정렬
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b){
					if(a < b)
						return 1;
					else if(a>b)
						return -1;
					return 0;
				}
			});

			System.out.println(solve());
		}
		br.close();
	}

	private static int solve(){
		int idx = 0;

		// 큐가 빌때까지
		while(!q.isEmpty()){
			// 맨 앞에꺼 꺼내기
			Print print = q.poll();

			// 꺼낸 요청의 중요도가 현재 남은 요청중 가장 높은 중요도와 같지 않다면
			// 뒤에 다시 넣기
			if(print.importance != list.get(idx)){
				q.offer(print);
			}
			else{
				// 꺼낸 요청의 중요도가 현재 남은 요청중 가장 높은 중요도라면
				// 거기에 찾고 있는 문서라면 탐색 끝
				if(print.idx == find){
					break;
				}
				// 제일 높은 중요도의 문서 처리가 끝났으니 다음으로 높은 중요도의 요청 찾기
				idx++;
			}
		}
		// 문서의 index는 0부터 시작
		// but 출력 순서는 1부터 시작하기 때문에 +1
		return idx+1;
	}
}