import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int a;
	int time;

	Pair(int a, int time){
		this.a = a;
		this.time = time;
	}
}

class Main {
	static int min;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);

		min = Integer.MAX_VALUE;
		cnt = 1;
		bfs(N, K);
		System.out.println(min);
		System.out.println(cnt);
	}

	private static void bfs(int a, int b){
		Queue<Pair> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		int time = 0;
		
		// 초기 설정
		visited[a] = true;
		q.offer(new Pair(a, time));

		// queue가 빌때까지 반복
		while(!q.isEmpty()){
			// 값 꺼내기
			Pair p = q.poll();			
			int aa = p.a;
			int tt = p.time;
			visited[aa] = true;

			// 동생을 잡은 경우
			if(aa == b){
				if(min > tt)
					min = tt;
				else if(min == tt)
					cnt++;
				continue;
			}

			// 이동할 수 있는 세가지 위치 모두 확인
			int dx1 = aa-1;
			int dx2 = aa+1;
			int dx3 = aa*2;

			// 이동할 수 있는 경우 queue에 넣고 방문처리
			if(dx1 >= 0 && !visited[dx1])
				q.offer(new Pair(dx1, tt+1));
			if(dx2 <= 100000 && !visited[dx2])
				q.offer(new Pair(dx2, tt+1));
			if(dx3 <= 100000 && !visited[dx3])
				q.offer(new Pair(dx3, tt+1));
		}

	}
}