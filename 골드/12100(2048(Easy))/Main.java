import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;

class Main {	
	static ArrayList<ArrayList<Integer>> list;
    static ArrayList<ArrayList<Integer>> after;
    static ArrayList<ArrayList<Integer>> backup;
    static int n;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int max = 0;
	static int[] arr = {0,1,2,3};
	static int[] t;
	static final int R = 5;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		n = Integer.parseInt(br.readLine());
		t = new int[4];

        list = initList();
        backup = initList();
        
		for(int i=0; i<n; i++){
			String[] temp2 = br.readLine().split(" ");
			for(int j=0; j<n; j++)
				list.get(i).add(Integer.parseInt(temp2[j]));	
        }
                
        backup = deepCopy(list);
		perm(0);
		System.out.println(max);
		br.close();
    }
	
	public static void perm(int k){
		if(k == 4){
			// 0:left, 1: right, 2: up, 3: down
			for(int i=0; i<4; i++){
                move(t[i]);
				if(isSame()){
                    list = deepCopy(backup);
					return;
				}
				else
					list = after;
			}
			
			ArrayList<ArrayList<Integer>> temp = initList();
			temp = list;
			for(int i=0; i<4; i++){
                move(i);
				after = list;
                list = temp;
            }
            list = deepCopy(backup);
			return;
		}
		else{
			for(int i=0; i<4; i++){
				t[k] = arr[i];
				perm(k+1);
			}
		}
	}

    public static void move(int cmd){
		after = deepCopy(list);
		switch(cmd){
			case 0:
				left(after);
                break;
            case 1:
				right(after);
                break;
			case 2:
                after = rotateClockwise(after);
                right(after);
				after = rotateCounterClockwise(after);
                break;
            case 3:
                after = rotateClockwise(after);
                left(after);
                after = rotateCounterClockwise(after);
		}
    }

    public static void left(ArrayList<ArrayList<Integer>> list){
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
				
				max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                j = idx;
            }

            if(list.get(i).size() != 0)
                max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                
            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0);
        }
    }

    public static void right(ArrayList<ArrayList<Integer>> list){
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
                
				max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
                j = idx;
            }

            if(list.get(i).size() != 0)
			    max = list.get(i).get(j) > max ? list.get(i).get(j) : max;
            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0,0);
        }
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

	public static boolean isSame(){
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
}