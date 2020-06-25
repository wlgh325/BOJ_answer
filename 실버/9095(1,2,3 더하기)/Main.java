import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int i=0; i<T; i++){
			int N = Integer.parseInt(br.readLine());
			add(N-1);
			add(N-2);
			add(N-3);
			System.out.println(ans);
			ans = 0;
		}
		br.close();
	}

	public static void add(int n){
		if(n < 0) return;
		if(n == 0){
			ans++;
			return;
		}
		add(n-1);
		add(n-2);
		add(n-3);
	}
}