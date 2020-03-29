import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
	static int n;
	static int[] durabilitis, weights;
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		durabilitis = new int[n];	// 내구도
		weights = new int[n];	// 무게

		// 입력받기
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			durabilitis[i] = Integer.parseInt(st.nextToken());
			weights[i] = Integer.parseInt(st.nextToken());
		}

		max = 0;
		solve(0);
		System.out.println(max);
	}

	private static void solve(int cnt){
		// 맨 오른쪽 계란에 도달한 경우
		// 깨진 계란 개수 세기
		if(cnt == n){
			int sum = 0;
			for(int i=0; i<n; i++)
				if(durabilitis[i] <= 0)
					sum++;

			max = max < sum ? sum : max;	// 최대값 갱신
			return;
		}

		boolean flag = false;
		// 깨진 계란을 들은 경우 다음 계란으로 넘어가기
		if(durabilitis[cnt] <= 0){
			solve(cnt+1);
			return;
		}
		else{
			// cnt 번째로 다른 계란 깨보기
			for(int i=0; i<n; i++){
				if(i != cnt && durabilitis[i] > 0 && durabilitis[cnt] > 0){
					flag = true;
					// 계란으로 계란치기
					durabilitis[i] -= weights[cnt];
					durabilitis[cnt] -= weights[i];

					// 다음 계란 이동
					solve(cnt+1);
					// 원상 복구
					durabilitis[i] += weights[cnt];
					durabilitis[cnt] += weights[i];
				}
			}
		}
		// 깨지지 않은 계란을 들었지만 깰 계란이 없는 경우 다음 게란으로 넘어가기
		if(!flag)
			solve(cnt+1);
	}
}