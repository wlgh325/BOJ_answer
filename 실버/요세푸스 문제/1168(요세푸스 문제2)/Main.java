import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();

		sb.append("<");
		for(int i=1; i<=n; i++)
			list.add(i);
		
		int idx = 0;
		while(list.size() > 1){
			// list의 k번째 사람의 숫자가 k+1이므로 k-1번째 사람(숫자가 k)을 없앤다.
			idx += m-1;

			// 원형 list처럼 동작
			if(idx >= list.size())
				idx %= list.size();
			sb.append(list.get(idx) + ", ");
			list.remove(idx);
		}
		// 마지막 element
		sb.append(list.get(0) + ">");
		System.out.println(sb);
	}
}