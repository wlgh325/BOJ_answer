import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Num{
	int idx;
	int num;

	Num(int idx, int num){
		this.idx = idx;
		this.num = num;
	}
}

class Main2 {
	static int N;
	static ArrayList<Num> list;
	static int[] tree;
	static int base;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		for(int i=0; i<N; i++)
			list.add(new Num(i+1, Integer.parseInt(br.readLine())));

		Collections.sort(list, new Comparator<Num>() {
			@Override
			public int compare(Num a, Num b){
				if(a.num < b.num)
					return -1;
				else if(a.num > b.num)
					return 1;
				return 0;
			}
		});

		int[] ans = new int[N+1];
		for(base=1; base < N; base<<=1);
		tree = new int[(int)(1 << (int)Math.ceil( Math.log10(base) / Math.log10(2) ) + 1)];

		for(Num n : list){
			int idx = n.idx;
			ans[idx] = idx - range_sum(1, idx-1);
			update(idx, 1);
		}
		for(int i=1; i<=N; i++)
			System.out.println(ans[i]);
	}

	private static void update(int idx, int val){
		idx += base - 1;	// base index 더해주기
		tree[idx] = val;
		while((idx >>= 1) != 0){
			tree[idx] = tree[idx<<1] + tree[idx<<1 | 1];
		}
	}
	
	private static int range_sum(int a, int b){
		int sum = 0;
		a += base - 1;
		b += base;
		while(a < b){
			if(a % 2 == 1) sum += tree[a]; a++;
			if(b % 2 == 0) sum += tree[b]; b--;
			a >>= 1; b >>= 1;
		}
		if(a==b) sum += tree[a];
		return sum;
	}
}