import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static long[] tree;
	static int base;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// tree 배열 생성하고 base index 구하기
		tree = new long[(int)(1 << (int)Math.ceil( Math.log10(N) / Math.log10(2) ) + 1)];
		for(base=1; base < N; base<<=1);

		// tree의 base index 부터 N개의 값 넣기
		for(int i=base; i<base + N; i++)
			tree[i] = Long.parseLong(br.readLine());

		// tree init
		for(int i=base-1; i>0; i--)
			tree[i] = tree[2*i] + tree[2*i + 1];

		// M+K개의 쿼리 처리하기
		for(int i=0; i<M+K; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a==1)
				update(b,c);	// b번쨰 수를 c로 바꾸기
			else if(a==2)
				System.out.println(range_sum(b,c));	// b번째 부터 c번째 까지 합 구하기
		}
	}

	private static void update(int idx, int val){
		idx += base - 1;	// base index 더해주기
		tree[idx] = val;
		while((idx >>= 1) != 0){
			tree[idx] = tree[idx<<1] + tree[idx<<1 | 1];
		}
	}
	
	private static long range_sum(int a, int b){
		long sum = 0;
		a += base - 1;
		b += base - 1;
		while(a < b){
			if(a % 2 == 1) sum += tree[a]; a++;
			if(b % 2 == 0) sum += tree[b]; b--;
			a >>= 1; b >>= 1;
		}
		if(a==b) sum += tree[a];
		return sum;
	}
}