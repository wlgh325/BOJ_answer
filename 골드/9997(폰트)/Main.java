import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	static int N;
	static int[] dic;
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dic = new int[N];
		for(int i=0; i<N; i++){
			String str = br.readLine();
			int temp = 0;
			for(int j=0; j< str.length(); j++){
				char c = str.charAt(j);
				temp |= (1 << (c - 'a'));
			}
			dic[i] = temp;
		}

		ans = 0;
		dfs(-1,0);
		System.out.println(ans);
	}

	public static void dfs(int r, int alpha){
		if(r == N-1){
			if(alpha == ((1 << 26) - 1))
				ans++;
			return;
		}
		dfs(r+1, alpha | dic[r+1]);
		dfs(r+1, alpha);
	}
}