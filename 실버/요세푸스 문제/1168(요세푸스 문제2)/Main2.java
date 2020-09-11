import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	static int[] segmentTree;
	static ArrayList<Integer> list;
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		int base;	// segment tree의 값을 처음 써 넣을 index 구하기
		for(base=1; base<n; base <<= 1);

		segmentTree = new int[(int)(1 << (int)Math.ceil( Math.log10(base) / Math.log10(2) ) + 1)];
		list = new ArrayList<>();

		for(int i=1; i<=n; i++)
			update(i,1,1,1,n);
		int mod = n -1;
		list.add(m);
		update(m,0,1,1,n);	// m 번째 수 지우기
		for(int i=2; i<=n; i++){
			// 1, 마지막으로 제거한 수, 1, 1, n
			int x = (squery(1, list.get(list.size()-1), 1, 1, n) + m) % mod;
			if(x == 0) x = mod;
			list.add(query(x,1,1,n));	// 현재 위치에서 m번째 사람 찾기?
			update(list.get(list.size()-1),0,1,1,n);	// 끝에 꺼 지우기
			mod--;
		}
		System.out.println(base);
	}

	private static int update(int pos, int val, int node, int x, int y) {
		if (pos < x || y < pos)
			return segmentTree[node];
		if (x == y)
			return segmentTree[node] = val;
		int mid = (x + y) >> 1;	// 나누기 2
		return segmentTree[node] = update(pos, val, node * 2, x, mid) + update(pos, val, node * 2 + 1, mid + 1, y);
	}

	private static int query(int val, int node, int x, int y) {
		if (x == y)
			return x;
		if (segmentTree[node * 2] >= val)
			return query(val, node * 2, x, (x + y) / 2);
		else
			return query(val - segmentTree[node * 2], node * 2 + 1, (x + y) / 2 + 1, y);
	}
	private static int squery(int lo, int hi, int node, int x, int y) {
		if (y < lo || hi < x)
			return 0;
		if (lo <= x&&y <= hi)
			return segmentTree[node];
		int mid = (x + y) >> 1;
		return squery(lo, hi, node * 2, x, mid) + squery(lo, hi, node * 2 + 1, mid + 1, y);
	}
}
