import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
	static int n;
	static int[] areaNum;
	static int[][] graph;
	static boolean[] visited;
	static int min = 9999;

	public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		areaNum = new int[n];
		st = new StringTokenizer(br.readLine());
		graph = new int[n][11];

		for(int i=0; i<n; i++)
			areaNum[i] = Integer.parseInt(st.nextToken());
	
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			graph[i][0] = num;
			for(int j=1; j<=num; j++)
				graph[i][j] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		// 1 ~ n-1개를 선택한 경우 모든 조합 찾기
		for(int i=1; i<n; i++){
			visited = new boolean[n];
			comb(0, i);
		}
		
		if(min == 9999)
			bw.write("-1");
		else
			bw.write("" + min);

        bw.flush();
        bw.close();
        br.close();
	}

	public static void comb(int start, int r){
		if(r==0){
			solve();
			return;
		}
		
		for(int i=start; i<n; i++){
			visited[i] = true;
			comb(i+1, r-1);
			visited[i] = false;
		}
	}

	public static void bfs(){
		Queue<Integer> aArea = new LinkedList<>();
		Queue<Integer> bArea = new LinkedList<>();
		int[] check = new int[n];

		// 선거구 나누기
		for(int i=0; i<n; i++){
			if(visited[i]){
				aArea.offer(i);
				check[i] = 1;
			}
			else{
				bArea.offer(i);
				check[i] = 2;
			}
		}

		int num = 0;
		// a 선거구 연결 확인
		while(!aArea.isEmpty()){
			int a = aArea.poll();

			// 연결된 곳 순회
			for(int i=1; i<=graph[a][0]; i++){
				if(check[graph[a][i]] == 1){
					num++;
				}
			}
		}

		// b 선거구 연결 확인
		boolean[] visited2 = new boolean[bArea.size()];
		while(!q.isEmpty()){
			int b = q
			visited[a] = true;
			for(int i=1; i<=graph[a][0]; i++){
				int b = graph[a][i];
				bfs(q, b, visited);
			}
		}

		if(isConnected(visited) && isConnected(visited2))
			min = Math.min(min, Math.abs(getNumArea(aArea) - getNumArea(bArea)));

		
	}

	public static boolean isConnected(boolean[] visited){
		boolean result = true;
		
		// 방문되지 않은 곳이 있다면 연결되지 않음
		for(int i=0; i<visited.length; i++)
			if(!visited[i])
				result = false;

		return result;
	}

	public static int getNumArea(ArrayList<Integer> area){
		int sum = 0;

		for(Integer a: area)
			sum += areaNum[a];

		return sum;
	}
}