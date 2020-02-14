import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Main {	
    static int n;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr = {0,1,2,3};
    static int[] branch_max;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		n = Integer.parseInt(br.readLine());
        branch_max = new int[11];
        
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
		if(k == 6)
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
			}
		}
    }
    
    public static ArrayList<ArrayList<Integer>> move(ArrayList<ArrayList<Integer>> list, int cmd){
		ArrayList<ArrayList<Integer>> after = deepCopy(list);
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
				
				//max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                j = idx;
            }

            // if(list.get(i).size() != 0)
            //     max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                
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
                
				//max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                j = idx;
            }

            // if(list.get(i).size() != 0)
			//     max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
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
            }
        }
        return reverse;
    }

    public static ArrayList<ArrayList<Integer>> rotateCounterClockwise(ArrayList<ArrayList<Integer>> list){        
        ArrayList<ArrayList<Integer>> reverse = initList();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                reverse.get(i).add(list.get(j).get(n-1-i));
            }
        }
        return reverse;
    }

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
		}

		return dest;
	}

	public static boolean isSame(ArrayList<ArrayList<Integer>> list, ArrayList<ArrayList<Integer>> after){
		int cnt = 0;
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(list.get(i).get(j) == after.get(i).get(j))
					cnt++;
			}
		}

		if(cnt == n*n)
			return true;
		else
			return false;
    }

    public static int getMax(ArrayList<ArrayList<Integer>> list){
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int temp = list.get(i).get(j);
                if(max < temp)
                    max = temp;
            }
        }
        return max;
    }
}