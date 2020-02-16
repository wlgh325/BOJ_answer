import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main2 {	
    static int n;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr = {0,1,2,3};
    static int[] branch_max;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		n = Integer.parseInt(br.readLine());
        branch_max = new int[11];
        
        int[][] map = new int[n][n];
		for(int i=0; i<n; i++){
			String[] temp = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				map[i][j] = Integer.parseInt(temp[j]);
        }

        
        branch_max[0] = getMax(map);
        backtracking(1, map);
        Arrays.sort(branch_max);
		System.out.println(branch_max[branch_max.length-1]);
		br.close();
    }   
	
	public static void backtracking(int k, int[][] map){
		if(k == 6)
			return;
		else{
			for(int i=0; i<4; i++){
                int[][] after = new int[n][n];
                after = move(map, i);
                if(isSame(map, after))
                    continue;
                int mm = getMax(after);
                // k branch에서 더 큰 값이 나오면 update
                branch_max[k] = branch_max[k] < mm ? mm : branch_max[k];

                // 이전 depth보다 작으면 continue
                if(i == 3 & branch_max[k-1] > mm)
                    continue;
                    
                    backtracking(k+1, after);
			}
		}
    }
    
    public static int[][] move(int[][] map, int cmd){
		int[][] after = deepCopy(map);
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
            }
        }
        return reverse;
    }

    public static int[][] rotateCounterClockwise(int[][] map){
        int[][] reverse = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                reverse[i][j] = map[j][n-1-i];
            }
        }
        return reverse;
    }

	public static int[][] deepCopy(int[][] src){
		int[][] dest = new int[n][n];
		for(int i=0; i<n; i++){
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}

		return dest;
	}

	public static boolean isSame(int[][] map, int[][] after){
		int cnt = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(map[i][j] == after[i][j])
					cnt++;
			}
		}

		if(cnt == n*n)
			return true;
		else
			return false;
    }

    public static int getMax(int[][] map){
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int temp = map[i][j];
                if(max < temp)
                    max = temp;
            }
        }
        return max;
    }
}