import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		sb.append("<");

		// q에 N명의 사람 넣기
		for(int i=1; i<=n; i++)
			q.offer(i);

		while(q.size() > 1){
			// k-1번 째 사람까지 queue에서 빼서 넣기
			for(int i=0; i<k-1; i++)
				q.offer(q.poll());

			sb.append(q.poll() + ", ");	// k번째 사람 제거
		}
		sb.append(q.poll() + ">");
		System.out.println(sb);
	}
}