import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
=======
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811

class Main {	
    static int n;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr = {0,1,2,3};
    static int[] branch_max;
<<<<<<< HEAD
=======
    static int max = 0;
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		n = Integer.parseInt(br.readLine());
        branch_max = new int[11];
        
<<<<<<< HEAD
        ArrayList<ArrayList<Integer>> list = initList();
		for(int i=0; i<n; i++){
			String[] temp2 = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				list.get(i).add(Integer.parseInt(temp2[j]));	
        }

        branch_max[0] = getMax(list);
        perm(1, list);
        Arrays.sort(branch_max);
		System.out.println(branch_max[branch_max.length-1]);
		br.close();
    }   
	
	public static void perm(int k, ArrayList<ArrayList<Integer>> list){
		if(k == 11)
			return;
		else{
			for(int i=0; i<4; i++){
                ArrayList<ArrayList<Integer>> after = initList();
                after = move(list, i);
                if(isSame(list, after))
                    continue;
                int mm = getMax(after);
                // k branch에서 더 큰 값이 나오면 update
                branch_max[k] = branch_max[k] < mm ? mm : branch_max[k];

                // 이전 depth보다 작으면 continue
                if(i == 3 & branch_max[k-1] > mm)
                    continue;
                    
                perm(k+1, after);
=======
        int[][] map = new int[n][n];
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				map[i][j] = Integer.parseInt(temp[j]);
        }

        max = getMax(map);
        backtracking(0, map);
        
        System.out.println(max);
        
		br.close();
    }   
	
	public static void backtracking(int k, int[][] map){
        // 현재의 최댓값 찾기
        int mm = getMax(map);
        if(mm <= branch_max[k])
            return;

		if(k == 10){
            max = Math.max(max, mm);
            int b = max;
            while(k > 0){
                branch_max[k--] = b;
                b /= 2;
            }
			return;
        }
		else{
			for(int i=0; i<4; i++){
                int[][] after = new int[n][n];
                after = move(map, i);
                
                // 변화가 없는 경우 가지치기
                if(isSame(map, after))
                    continue;
                    
                backtracking(k+1, after);
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
			}
		}
    }
    
<<<<<<< HEAD
    public static ArrayList<ArrayList<Integer>> move(ArrayList<ArrayList<Integer>> list, int cmd){
		ArrayList<ArrayList<Integer>> after = deepCopy(list);
=======
    public static int[][] move(int[][] map, int cmd){
		int[][] after = deepCopy(map);
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
		switch(cmd){
			case 0:
				after = left(after);
                break;
            case 1:
				after = right(after);
                break;
			case 2:
                after = rotateClockwise(after);
                after = right(after);
				after = rotateCounterClockwise(after);
                break;
            case 3:
                after = rotateClockwise(after);
                after = left(after);
                after = rotateCounterClockwise(after);
        }
        return after;
    }

<<<<<<< HEAD
    public static ArrayList<ArrayList<Integer>> left(ArrayList<ArrayList<Integer>> list){
        // 왼쪽에서 부터 확인
        for(int i=0; i<n; i++){
            for(int k=0; k<list.get(i).size()-1; k++)
                if(list.get(i).get(k) == 0){
                    list.get(i).remove(k);
                    k--;
                }
            int j = 0;
            while(j < list.get(i).size()-1){
                int left = list.get(i).get(j);
                int idx = j+1;
                
                int right = list.get(i).get(idx);

                // 값이 갖다면 합친다.
                if(left == right){
                    list.get(i).set(j, right*2);
                    list.get(i).remove(idx);
                    list.get(i).add(0);
                }

                j = idx;
            }
                
            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0);
        }
        return list;
    }

    public static ArrayList<ArrayList<Integer>> right(ArrayList<ArrayList<Integer>> list){
        // 위에부터 확인
        // 오른쪽에서 부터 확인

        for(int i=0; i<n; i++){
            // 0을 다 없앰
            for(int k=0; k<=list.get(i).size()-1; k++)
                if(list.get(i).get(k) == 0){
                    list.get(i).remove(k);
                    k--;
                }

            int j = list.get(i).size() - 1;
            while(j > 0){
                int right = list.get(i).get(j);
                int idx = j-1;
                
                int left = list.get(i).get(idx);

                // 값이 갖다면 합친다.
                if(left == right){
                    list.get(i).set(j, right*2);
                    list.get(i).remove(idx);
                    list.get(i).add(0, 0);
                }

                j = idx;
            }

            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0,0);
        }
        return list;
    }

    public static ArrayList<ArrayList<Integer>> rotateClockwise(ArrayList<ArrayList<Integer>> list){        
        ArrayList<ArrayList<Integer>> reverse = initList();
        for(int i=0; i<n; i++){
            for(int j=n-1; j>=0; j--){
                reverse.get(i).add(list.get(j).get(i));
=======
    public static int[][] left(int[][] map){
        Queue<Integer> q = new LinkedList<>();
        int[][] newMap = new int[n][n];

        // 왼쪽에서 부터 확인
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] != 0)
                    q.offer(map[i][j]);
            }
            int idx = 0;
            while(!q.isEmpty()){
                int value = q.poll();
                if(newMap[i][idx] == 0)
                    newMap[i][idx] = value;
                else if(newMap[i][idx] == value)
                    newMap[i][idx++] += value;
                else
                    newMap[i][++idx] = value;
            }
        }
        return newMap;
    }

    public static int[][] right(int[][] map){
        // 위에부터 확인
        // 오른쪽에서 부터 확인
        Queue<Integer> q = new LinkedList<>();
        int[][] newMap = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=n-1; j>=0; j--){
                if(map[i][j] != 0)
                    q.offer(map[i][j]);
            }
            int idx = n-1;
            while(!q.isEmpty()){
                int value = q.poll();
                if(newMap[i][idx] == 0)
                    newMap[i][idx] = value;
                else if(newMap[i][idx] == value)
                    newMap[i][idx--] += value;
                else
                    newMap[i][--idx] = value;
            }
        }
        return newMap;
    }

    public static int[][] rotateClockwise(int[][] map){
        int[][] reverse = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=n-1; j>=0; j--){
                reverse[i][n-j-1] = map[j][i];
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
            }
        }
        return reverse;
    }

<<<<<<< HEAD
    public static ArrayList<ArrayList<Integer>> rotateCounterClockwise(ArrayList<ArrayList<Integer>> list){        
        ArrayList<ArrayList<Integer>> reverse = initList();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                reverse.get(i).add(list.get(j).get(n-1-i));
=======
    public static int[][] rotateCounterClockwise(int[][] map){
        int[][] reverse = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                reverse[i][j] = map[j][n-1-i];
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
            }
        }
        return reverse;
    }

<<<<<<< HEAD
    public static ArrayList<ArrayList<Integer>> initList(){
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        for(int i=0; i<n; i++){
            board.add(new ArrayList<Integer>());
        }
        return board;
    }

	public static ArrayList<ArrayList<Integer>> deepCopy(ArrayList<ArrayList<Integer>> src){
		ArrayList<ArrayList<Integer>> dest = new ArrayList<>();
		
		for(int i=0; i<n; i++){
			ArrayList<Integer> list = new ArrayList<>();
			for(int j=0; j<n; j++){
				list.add(src.get(i).get(j));
			}
			dest.add(list);
=======
	public static int[][] deepCopy(int[][] src){
		int[][] dest = new int[n][n];
		for(int i=0; i<n; i++){
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
		}

		return dest;
	}

<<<<<<< HEAD
	public static boolean isSame(ArrayList<ArrayList<Integer>> list, ArrayList<ArrayList<Integer>> after){
		int cnt = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(list.get(i).get(j) == after.get(i).get(j))
=======
	public static boolean isSame(int[][] map, int[][] after){
		int cnt = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(map[i][j] == after[i][j])
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
					cnt++;
			}
		}

		if(cnt == n*n)
			return true;
		else
			return false;
    }

<<<<<<< HEAD
    public static int getMax(ArrayList<ArrayList<Integer>> list){
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int temp = list.get(i).get(j);
=======
    public static int getMax(int[][] map){
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int temp = map[i][j];
>>>>>>> 9953dc10900ac0733526288e2bc6f3d3f8700811
                if(max < temp)
                    max = temp;
            }
        }
        return max;
    }
}