import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static boolean[] visited;
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		// 2차원 arrayList 초기화
		graph = new ArrayList<>();
		for(int i=0; i<N+1; i++)
			graph.add(new ArrayList<Integer>());

		// 값 입력받기
		for(int i=0; i<M; i++){
			temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		// 방문
		int cnt = 0;
		visited = new boolean[N+1];
		for(int i=1; i<=N; i++){
			if(!visited[i]){
				bfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
		br.close();
	}

	public static void bfs(int start){
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;

		q.offer(start);
		while(!q.isEmpty()){
			int p = q.poll();
			
			// 연결된 점들 모두 탐색
			// 방문하지 않았다면 방문하고 다음 번 탐색을 위해 queue에 넣기
			for(int b : graph.get(p)){
				if(!visited[b]){
					visited[b] = true;
					q.offer(b);
				}
			}
		}
	}
}