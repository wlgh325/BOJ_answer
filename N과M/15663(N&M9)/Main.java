import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

class Main{
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static Set<String> hash;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		
		// 두번째 줄 입력받기
		String[] temp2 = br.readLine().split(" ");
		
		// 배열로 변환
		Integer[] arr = new Integer[n];
		
		// 값 배열에 저장
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(temp2[i]);
		}
		
		boolean[] visited = new boolean[n];
		String[] output = new String[m];
		
		hash = new LinkedHashSet<>();
		Arrays.sort(arr);
		permu(arr, output, visited, 0, n, m);
		
		// 반례 (string sort는 숫자와 다름)
		// 2 2
		// 9 10

		// 중복 제거된 순열을 배열로 변환
		String[] ans = hash.toArray(new String[hash.size()]);		
		
	
		for(int i=0; i<hash.size(); i++) {
			String[] temp3 = ans[i].split("/");
			for(int j=0; j<temp3.length; j++) {
				bw.write(temp3[j]);
				if(j != m-1)
					bw.write(" ");
			}
			bw.newLine();
		}

		bw.flush();		
		br.close();
		bw.close();
	}
	
	public static void permu(Integer[] arr, String[] output, boolean[] visited, int depth, int n, int r) throws IOException {
		if(depth == r) {
			addHash(output, visited, r);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i] ){
				visited[i] = true;
				output[depth] = arr[i].toString();
				permu(arr, output, visited, depth+1 , n, r);
				visited[i] = false;	
			}
		}
	}
	
	public static void addHash(String[] output, boolean[] visited, int r) {
		String str = "";
		
		for(int i=0; i<r; i++) {
			if(i != r-1)
				str += output[i] + "/";
			else
				str += output[i];
		}
		hash.add(str);
	}
}