import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main2 {
	static int n, m;
	static int[] parent, size;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parent = new int[n];
		size = new int[n];
		for(int i=0; i<n; i++){
			parent[i] = i;
			size[i] = 1;
		}

		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(!isSameParent(a, b))
				union(a,b);
		}

		System.out.println(size[findParent(0)] - 1);
	}

	private static int findParent(int x){
		if(parent[x] == x) return x;
		else
			return parent[x] = findParent(parent[x]);
	}

	private static boolean isSameParent(int x, int y){
		x = findParent(x);
		y = findParent(y);
		if(x != y) return false;
		return true;
	}

	private static void union(int x, int y){
		x = findParent(x);
		y = findParent(y);

		// 부모를 합쳐주기
		if(x != y){
			parent[y] = x;
			size[x] += size[y];
		}
	}
}