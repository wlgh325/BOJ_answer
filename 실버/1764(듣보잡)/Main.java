import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	static int N, M;
	static ArrayList<String> ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));

		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);

		ArrayList<String> arr = new ArrayList<>();
		for(int i=0; i<N; i++)
			arr.add(br.readLine());

		// 이분 탐색을 위해 정렬
		quickSort(arr, 0, arr.size()-1);
		ans = new ArrayList<>();
		for(int i=0; i<M; i++){
			String str = br.readLine();
			// 이분탐색을 통해 찾은 경우 ans에 저장
			if(binarySearch(arr, str)){
				ans.add(str);
			}
		}
		
		// 갯수출력
		bw.write(ans.size() + " ");
		bw.newLine();

		// 하나라도 있는 경우에만 정렬
		if(ans.size() != 0)
			quickSort(ans, 0, ans.size()-1);

		// 출력
		for(String s : ans){
			bw.write(s);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void quickSort(ArrayList<String> list, int l, int r){
		int left = l;
		int right = r;
		String pivot = list.get((l+r)/2);

		do{
			while(list.get(left).compareTo(pivot) < 0) left++;
			while(list.get(right).compareTo(pivot) > 0) right--;
			if(left <= right){
				String temp = list.get(left);
				list.set(left, list.get(right));
				list.set(right, temp);
				left++;
				right--;
			}
		}while(left <= right);

		if(l < right) quickSort(list, l, right);
		if(r > left) quickSort(list, left, r);
	}

	public static boolean binarySearch(ArrayList<String> list, String target){
		int left = 0;
		int right = list.size()-1;
		boolean flag = false;
		
		while(left <= right){
			int mid = (left + right) / 2;
			if(list.get(mid).compareTo(target) > 0)
				right = mid-1;
			else if(list.get(mid).compareTo(target) < 0)
				left = mid+1;
			else{
				flag = true;
				break;
			}
		}
		
		return flag;
	}
}