import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


class Main {
	static int r, c, k;
	static int[][] arr;
	static int rowNum, colNum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		r = Integer.parseInt(temp[0]);
		c = Integer.parseInt(temp[1]);
		k = Integer.parseInt(temp[2]);
		
		arr = new int[3][3];
		rowNum = 3;
		colNum = 3;
		for(int i=0; i<3; i++) {
			temp = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		int time = 0;
		while(true) {
			if(r-1 < rowNum && c-1 < colNum) {
				if(arr[r-1][c-1] == k) break;				
			}
			if(time > 100) break;

			// R 연산
			if(rowNum >= colNum) {
				arr = rOperation();
			}
			else {
				// C 연산
				arr = cOperation();
			}
			time++;
		}
		
		if(time > 100) System.out.println(-1);
		else System.out.println(time);
	}
	
	// 각각 수가 몇개 있는지 확인
	public static int[][] rOperation() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[][] newArr = new int[rowNum][];
		int maxLen = 0;
		
		for(int i=0; i<rowNum; i++) {
			for(int j=0; j<colNum; j++) {
				int num = arr[i][j];
				if(num != 0) {
					if(map.containsKey(num)) {
						map.put(num, map.get(num)+1);
					}
					else {
						map.put(num, 1);
					}
					
				}				
			}
			

			// 새로운 배열 만들기전 정렬
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
			
			// 개수 기준 오름차순 정렬
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
					// 오름 차순 정렬
					if(obj1.getValue().compareTo(obj2.getValue()) > 0) {
						return 1;
					}
					else if(obj1.getValue().compareTo(obj2.getValue()) < 0) {
						return -1;
					}
					else {
						return obj1.getKey().compareTo(obj2.getKey());						
					}
				}
			});
			
			newArr[i] = new int[list_entries.size()*2];
			int idx = 0;
			int size = list_entries.size();
			maxLen = maxLen < size*2 ? size*2 : maxLen;
			
			// 열이 100을 넘는 경우, 100까지만
			maxLen = maxLen > 100 ? 100 : maxLen;
			
			for(int k=0; k<size; k++) {
				newArr[i][idx++] = list_entries.get(k).getKey();
				newArr[i][idx++] = list_entries.get(k).getValue();
				
				if(idx == 100) break;
			}
			
			map.clear();
		}
		      
		colNum = maxLen;
		// 0 붙여 넣기
		for(int i=0; i<rowNum; i++) {
			int[] temp = new int[maxLen];
			int[] row;
			
			if(maxLen > newArr[i].length) {
				row = Arrays.copyOf(newArr[i], newArr[i].length);
				for(int j=0; j<row.length; j++) {
					temp[j] = row[j];
				}
				newArr[i] = temp;				
			}
		}
		
		return newArr;
	}
	
	public static int[][] cOperation() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[][] newArr = new int[colNum][];
		int maxLen = 0;
		
		for(int i=0; i<colNum; i++) {
			for(int j=0; j<rowNum; j++) {
				int num = arr[j][i];
				if(num != 0) {
					if(map.containsKey(num)) {
						map.put(num, map.get(num)+1);
					}
					else {
						map.put(num, 1);
					}		
				}
			}
			
			// 새로운 배열 만들기전 정렬
			List<Entry<Integer, Integer>> list_entries = new ArrayList<Entry<Integer, Integer>>(map.entrySet());
			
			// 개수 기준 오름차순 정렬
			Collections.sort(list_entries, new Comparator<Entry<Integer, Integer>>() {
				// compare로 값을 비교
				public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
					// 오름 차순 정렬
					if(obj1.getValue().compareTo(obj2.getValue()) > 0) {
						return 1;
					}
					else if(obj1.getValue().compareTo(obj2.getValue()) < 0) {
						return -1;
					}
					else {
						return obj1.getKey().compareTo(obj2.getKey());						
					}
				}
			});
			
			newArr[i] = new int[list_entries.size()*2];
			int idx = 0;
			int size = list_entries.size();
			maxLen = maxLen < size*2 ? size*2 : maxLen;
			
			// 열이 100을 넘는 경우, 100까지만
			maxLen = maxLen > 100 ? 100 : maxLen;
			
			for(int k=0; k<size; k++) {
				newArr[i][idx++] = list_entries.get(k).getKey();
				newArr[i][idx++] = list_entries.get(k).getValue();
				
				if(idx == 100) break;
			}
			
			map.clear();
		}
		
		rowNum = colNum;
		colNum = maxLen;
		// 0 붙여 넣기
		for(int i=0; i<rowNum; i++) {
			int[] temp = new int[maxLen];
			int[] row;
			
			if(maxLen > newArr[i].length) {
				row = Arrays.copyOf(newArr[i], newArr[i].length);
				for(int j=0; j<row.length; j++) {
					temp[j] = row[j];
				}
				newArr[i] = temp;				
			}
		}
		
		// 반전 시키기
		int[][] returnArr = new int[colNum][rowNum];
		
		for(int i=0; i<colNum; i++) {
			for(int j=0; j<rowNum; j++) {
				returnArr[i][j] = newArr[j][i];
			}
		}

		int temp = rowNum;
		rowNum = colNum;
		colNum = temp;
		
		return returnArr;
	}
}