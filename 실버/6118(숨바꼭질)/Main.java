import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 그래프 연결정보 입력받기
		adj = new ArrayList<>();
		for(int i=0; i<=N; i++)
			adj.add(new ArrayList<>());

		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		dijkstra(1);
	}
	
	private static void dijkstra(int v){
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];

		// distance 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 시작점 방문표시 및 거리 0 초기화
		visited[v] = true;
		dist[v] = 0;

		// 시작점과 연결된 노드의 거리 갱신
		for(int i=0; i<adj.get(v).size(); i++){
			int x = adj.get(v).get(i);
			if(!visited[x])
				dist[x] = 1;
		}

		// 모든 점에 대해서
		for(int a=0; a<N-1; a++){
			int min = Integer.MAX_VALUE;
			int minPos = -1;

			// 방문하지 않은 노드 중 dist 최소 값 찾기
			for(int i=1; i<=N; i++){
				if(!visited[i] && dist[i] != Integer.MAX_VALUE){
					if(dist[i] < min){
						min = dist[i];
						minPos = i;
					}
				}
			}

			// 최소 거리 가지는 점 방문처리
			visited[minPos] = true;

			// minPos와 연결되었으면서 방문하지 않은 점 check
			for(int i=0; i<adj.get(minPos).size(); i++){
				int x = adj.get(minPos).get(i);
				if(!visited[x]){
					if(dist[x] > dist[minPos] + 1)
						dist[x] = dist[minPos] + 1;
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