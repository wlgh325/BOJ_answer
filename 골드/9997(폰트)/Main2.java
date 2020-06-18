import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main2 {
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
		for(int r=1; r<=N; r++)
			dfs(0, r, 0);
		System.out.println(ans);
	}

	public static void dfs(int idx, int r, int alpha){
		if(r == 0){
			if(alpha == ((1 << 26) - 1))
				ans++;
			return ;
		}

		for(int j=idx; j<N; j++){
			int temp = alpha;
			temp |= dic[j];
			dfs(j+1, r-1, temp);
		}
	}
}