import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main2 {
	static int N, M;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 그래프 연결정보 입력받기
		adj = new ArrayList[N+1];
		for(int i=0; i<=N; i++)
			adj[i] = new ArrayList<>();

		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(b);
			adj[b].add(a);
		}

		dijkstra(1);
	}

	private static void dijkstra(int v){
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		
		Queue<Integer> q = new LinkedList<>();
		// 시작점 방문처리
		dist[v] = 0;
		visited[v] = true;

		// 시작점과 연결된 점들에 대해 검색하기 위해 넣기
		q.offer(v);

		while(!q.isEmpty()){
			int idx = q.poll();
			
			// 연결된 점들에 대해서 더 짧은 거리로 갱신해 나가기
			for(int i=0; i<adj[idx].size(); i++){
				int next = adj[idx].get(i);
				if(!visited[next] && dist[next] > dist[idx] + 1){
					dist[next] = dist[idx] + 1;
					// 갱신된 점과 연결된 점 체크를 위해 queue에 넣기
					q.offer(next);
					visited[next] = true;
				}
			}
		}

		int max = -1;
		int maxPos = -1;
		int maxNum = 1;
		// 최댓값 찾기
		for(int i=1; i<=N; i++){
			if(max < dist[i]){
				max = dist[i];
				maxPos = i;
				maxNum = 1;
			}
			else if(max == dist[i])
				maxNum++;
		}

		System.out.println(maxPos + " " + max + " " + maxNum);
	}
}