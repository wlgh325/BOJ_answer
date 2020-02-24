import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.IOException;

class Main {	
    static int vertexNum;
    static int a, b;    
    static int[] parent;
    static ArrayList<ArrayList<Integer>> child;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
			// 입력받기
            vertexNum = Integer.parseInt(br.readLine());
            child = new ArrayList<ArrayList<Integer>>();
            for(int i=0; i<vertexNum+1; i++)
                child.add(new ArrayList<Integer>());
            
            parent = new int[vertexNum+1];
                
			// 주어진 점들로 트리 만들기
            for(int i=0; i<vertexNum-1; i++){
				String[] temp = br.readLine().split(" ");
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);
                parent[b] = a;
                child.get(a).add(b);
			}
			
			// 공통 조상을 구할 두 노드
			String[] temp2 = br.readLine().split(" ");
			a = Integer.parseInt(temp2[0]);
			b = Integer.parseInt(temp2[1]);
            
            int a_depth = get_depth(a);
            int b_depth = get_depth(b);
            
            int same = solve(a, a_depth, b, b_depth);
            System.out.println(same);
        }
              
        bw.flush();
        bw.close();
		br.close();
    }

    static int solve(int a, int a_depth, int b, int b_depth){
        // 둘의 depth가 같아질 때까지 위로 올린다.
        if(a_depth > b_depth){
            while(a_depth != b_depth){
                a_depth--;
                a = parent[a];
            }
        }
        else if(a_depth < b_depth){
            while(a_depth != b_depth){
                b_depth--;
                b = parent[b];
            }
        }

        
        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    static int get_depth(int n){
        int ret = 0;
        int cur = n;
        while(cur != 0) {
            ret++;
            cur = parent[cur];
        }
        return ret-1;
     }
}