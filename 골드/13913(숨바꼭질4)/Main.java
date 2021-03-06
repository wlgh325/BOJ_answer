import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int a;
	int time;
	List<Integer> list;

	Pair(int a, int time, List<Integer> list){
		this.a = a;
		this.time = time;
		this.list = list;
	}
}

class Main {
	static int min;
	static int cnt;
	static List<Integer> minList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);

		// N이 K보다 큰 경우에는 -1씩 N-K만큼 뒤로 가야 한다.
		// 이 방법 한 가지 뿐이다.
		if(N > K){
			System.out.println(N-K);
			for(int i=N; i>=K; i--)
				sb.append(i).append(" ");
			System.out.println(sb);
		}
		else{
			min = Integer.MAX_VALUE;
			minList = new ArrayList<>();
			bfs(N, K);
			System.out.println(min);
			for(int val : minList)
				sb.append(val).append(" ");
			System.out.println(sb);
		}
	}

	private static void bfs(int a, int b){
		Queue<Pair> q = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		int time = 0;
		List<Integer> temp = new ArrayList<>();
		temp.add(a);
		// 초기 설정
		visited[a] = true;
		q.offer(new Pair(a, time, temp));

		// queue가 빌때까지 반복
		while(!q.isEmpty()){
			// 값 꺼내기
			Pair p = q.poll();			
			int aa = p.a;
			int tt = p.time;
			List<Integer> list = p.list;

			// 동생을 잡은 경우
			if(aa == b){
				if(min > tt){
					min = tt;
					minList = list;
				}
				continue;
			}

			// 이동할 수 있는 세가지 위치 모두 확인
			int dx1 = aa-1;
			int dx2 = aa+1;
			int dx3 = aa*2;

			// 이동할 수 있는 경우 queue에 넣고 방문처리
			if(dx1 >= 0 && !visited[dx1]){
				q.offer(new Pair(dx1, tt+1, copy(list, dx1)));
				visited[dx1] = true;
			}
			if(dx2 <= 100000 && !visited[dx2]){
				q.offer(new Pair(dx2, tt+1, copy(list, dx2)));
				visited[dx2] = true;
			}
			if(dx3 <= 100000 && !visited[dx3]){
				q.offer(new Pair(dx3, tt+1, copy(list,dx3)));
				visited[dx3] = true;
			}
		}
	}

	private static List<Integer> copy(List<Integer> list, int dx){
		List<Integer> dest = new ArrayList<>(list);
		dest.add(dx);
		return dest;
	}
}