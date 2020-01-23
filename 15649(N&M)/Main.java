import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		
		boolean[] visited = new boolean[n];
		int[] arr = new int[n];
		int[] output = new int[m];
		
		// 배열 생성
		for(int i=1; i<=n; i++) {
			arr[i-1] = i;
		}
		
		permu(arr, output, visited, 0, n, m);
		br.close();
	}
	
	public static void permu(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
		if(depth== r) {
			print(output, r);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				permu(arr, output, visited, depth +1 , n, r);
				visited[i] = false;
			}
		}
	}
	
	static void print(int[] arr, int r) {
        for(int i=0; i<r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}