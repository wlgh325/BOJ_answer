import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
	static long[] tree;
	static long[] lazy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// tree 배열 생성하고 base index 구하기
		tree = new long[(int)(1 << (int)Math.ceil( Math.log10(N) / Math.log10(2) ) + 1)];
		lazy = new long[(int)(1 << (int)Math.ceil( Math.log10(N) / Math.log10(2) ) + 1)];
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		init(arr, 1, 1, N);
		// M+K개의 쿼리 처리하기
		for(int i=0; i<M+K; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			if(a==1){
				int d = Integer.parseInt(st.nextToken());
				update_range(1, 1, N, left, right, d);
			}
			else if(a==2){
				bw.write(sum(1, 1, N, left, right) + "");
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long init(int[] arr, int node, int start, int end) {
		// leaf 노드라면 값을 쓴다.
		if (start == end) {
			return tree[node] = arr[start];
		} else {
			// leaft가 아니라면 왼쪽 node와 오른쪽 node의 합을 구한다.
			int mid = (start + end) / 2;
			return tree[node] = init(arr, node*2, start, mid) + init(arr, node*2+1, mid+1, end);
		}
	}

	private static void update_lazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end-start+1)*lazy[node];
			// leaf가 아니면
			if (start != end) {
				lazy[node*2] += lazy[node];
				lazy[node*2+1] += lazy[node];
			}
			lazy[node] = 0;	// update했으니까 lazy값 없애줌
		}
	}

	private static void update_range(int node, int start, int end, int left, int right, long diff) {
		update_lazy(node, start ,end);
		if(left > end || right < start)
			return;
		// 변경해야 하는 구간에 모두 포함되는 경우
		// ex) 3~7(left~right)을 변경하려는데 start~end가 3~4인 경우 변경하려는 구간에 포함
		if(left <= start && end <= right){
			// 구간에 모두 포함되기 때문에 구간의 크기*diff 만큼 더해줍니다.
			// 3,4 모두 diff 를 더하기 때문에, 3~4의 더한 값을 저장하고 있는 노드는 (4-3+1)*diff 만큼 더해야 합니다.
			tree[node] += (end-start+1)*diff;
			// 더 이상 update를 수행하지 않고 나중에 다시 업데이트를 수행하러 그 노드를 방문했을 때 업데이트 한다.
			// 그러기 위해서 leaf노드가 아니라면 자식에게 lazy 값을 물려준다.
			if(start != end){
				lazy[node*2] += diff;
				lazy[node*2 + 1] += diff;
			}
			return;
		}
		int mid = ((start + end) / 2);
		update_range(node*2, start, mid, left, right, diff);
		update_range(node*2+1, mid+1, end, left, right, diff);
		tree[node] = tree[node*2] + tree[node*2+1];	// 리프 노드가 아닌 경우에는 두 자식의 합 계산
	}

	private static long sum(int node, int start, int end, int left, int right) {
		update_lazy(node, start, end);

		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
	}
}