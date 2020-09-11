import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Main {	
	static ArrayList<ArrayList<Integer>> list;
	static int[] colors;
	static int V, E;
	static final int BLUE = -1;
	static final int RED = 1;
	static boolean flag;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc=1; tc<=T; tc++){
			String[] temp = br.readLine().split(" ");
			V = Integer.parseInt(temp[0]);	// 정점의 개수
			E = Integer.parseInt(temp[1]);	// 간선의 개수

			list = new ArrayList<>();
			colors = new int[V+1];
			
			for(int i=0; i<V+1; i++)
				list.add(new ArrayList<>());
			
			// 그래프 생성
			for(int i=0; i<E; i++){
				temp = br.readLine().split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				
				list.get(a).add(b);
				list.get(b).add(a);
			}
			
			flag = true;
			// 연결/비연결 그래프 모두 고려
			// 그래프가 비연결 그래프인 경우 모든 정점에 대해서 확인 필요
			for(int i=1; i<V+1; i++){
				if(!flag)
					break;
				if(colors[i] == 0)
					bfs(i);
			}

			if(!flag) System.out.println("NO");
			else System.out.println("YES");
		}
	}

	public static void bfs(int start){
		Queue<Integer> q = new LinkedList<>();

		q.offer(start);
		colors[start] = BLUE;

		while(!q.isEmpty()){
			int a = q.poll();
			int color = colors[a]==BLUE?RED:BLUE;

			for(Integer num :list.get(a)){
				// 방문하지 않았으면 다른 색으로 색칠
				if(colors[num] == 0){
					colors[num] = color;
					q.offer(num);
				}
				else{
					// 인접 노드를 이미 방문했는데 같은 색이면 이분 그래프 X
					if(colors[num] == colors[a]) flag = false;
				}
			}

			if(!flag) return;
		}
	}
}